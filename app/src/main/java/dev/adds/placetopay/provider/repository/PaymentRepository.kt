package dev.adds.placetopay.provider.repository

import android.util.Log
import com.google.gson.Gson
import dev.adds.placetopay.model.database.dao.TransactionDao
import dev.adds.placetopay.model.database.entities.*
import dev.adds.placetopay.model.domain.payment.ReferenceModel
import dev.adds.placetopay.provider.PaymentProvider
import dev.adds.placetopay.provider.services.ApiService
import dev.adds.placetopay.usescase.model.*
import dev.adds.placetopay.util.Constants
import javax.inject.Inject

class PaymentRepository @Inject constructor(
    private val api: ApiService,
    private val paymentProvider: PaymentProvider,
    private val dao: TransactionDao
) {

    suspend fun getProcessResponse(processItem: ProcessItem) : ProcessResponseItem =
        api.getProcessResponse(processItem.toModel()).toDomain()


    suspend fun register(response: ProcessResponseItem): Long {
        return dao.insert(TransactionEntity(provider = response.provider?: String(),
            reference = response.reference, internalReference = response.internalReference))
    }
    suspend fun register(amountItem: AmountItem, transactionId: Long): Long{
        return  dao.insert(AmountEntity(currency = amountItem.currency ,
            total = amountItem.total, transactionOwnerId = transactionId))
    }
    suspend fun register(cardItem: CardItem, transactionId: Long): Long =
        dao.insert(CardEntity(number = cardItem.number,
            expiration = cardItem.expiration,
            transactionOwnerId = transactionId))

    suspend fun register(payerItem: PayerItem, transactionId: Long): Long =
        dao.insert(PayerEntity(name = payerItem.name,
            surname = payerItem.surname,
            email = payerItem.email,
            documentType = payerItem.documentType,
            document = payerItem.document,
            mobile = payerItem.mobile, transactionOwnerId = transactionId))

    suspend fun register(statusItem: StatusItem, transactionId: Long): Long =
        dao.insert(StatusEntity(status = statusItem.status,
            message = statusItem.message,
            reason = statusItem.reason,
            date = statusItem.date, transactionOwnerId = transactionId))

    suspend fun getAllPayments(): List<ShoppingItem> =
        dao.getAllTransactions().map { it.toDomain() }

    suspend fun getAllPendingPayments(): MutableList<ShoppingItem> {
        val pending = dao.getAllPendingTransactions()
        return if(pending.isNullOrEmpty())
            mutableListOf()
        else
            dao.getAllPendingTransactions()!!.map { it.toDomain() } as MutableList<ShoppingItem>
    }


    fun addPayment(shoppingItem: ShoppingItem){
        paymentProvider.shoppingModel.add(shoppingItem.toModel())
    }

    suspend fun removePayment(shoppingItem: ShoppingItem): Boolean {
        val transaction = dao.getTransactionByReference(shoppingItem.processResponseItem.internalReference)
        return dao.remove(transaction!!.transaction) > 0
    }

    private suspend fun updatePayment(shoppingItem: ShoppingItem): Boolean {
        val transaction = dao.getTransactionByReference(shoppingItem.processResponseItem.internalReference)
        return dao.insert(transaction!!.status.apply {
            status = shoppingItem.processResponseItem.statusItem.status
            reason = shoppingItem.processResponseItem.statusItem.reason
            message = shoppingItem.processResponseItem.statusItem.message
            date = shoppingItem.processResponseItem.statusItem.date
        }) > 0
    }

    suspend fun tryTransaction(shoppingItem: ShoppingItem): ShoppingItem {
        if(shoppingItem.processResponseItem.statusItem.status != Constants.StatusResponse.PROCESSING.status){
            val response = api.getQueryResponse(ReferenceModel(shoppingItem.processResponseItem.internalReference))
            Log.i("tryTransaction", Gson().toJson(response))
            shoppingItem.processResponseItem.statusItem = response.statusModel.toDomain()
            updatePayment(shoppingItem)
        }
        else
            Log.i(Constants.DEBUG_KEY, "Register with getProcessResponse")

        return shoppingItem
    }
}