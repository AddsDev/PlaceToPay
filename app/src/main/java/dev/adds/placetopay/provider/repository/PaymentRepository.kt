package dev.adds.placetopay.provider.repository

import dev.adds.placetopay.model.database.dao.TransactionDao
import dev.adds.placetopay.model.database.entities.*
import dev.adds.placetopay.provider.PaymentProvider
import dev.adds.placetopay.provider.ProcessProvider
import dev.adds.placetopay.provider.services.ApiService
import dev.adds.placetopay.usescase.model.*
import javax.inject.Inject

class PaymentRepository @Inject constructor(
    private val api: ApiService,
    private val paymentProvider: PaymentProvider,
    private val processProvider: ProcessProvider,
    private val dao: TransactionDao
) {

    suspend fun getProcessResponse(processItem: ProcessItem) : ProcessResponseItem =
        api.getProcessResponse(processItem.toModel()).toDomain()


    suspend fun register(response: ProcessResponseItem): Long {
        return dao.insert(TransactionEntity(provider = response.provider?: String(),
            reference = response.reference))
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

    suspend fun getAllPayments(): List<ShoppingItem>{
        return dao.getAllTransactions().map { it.toDomain() }
    }

    fun addPayment(shoppingItem: ShoppingItem){
        paymentProvider.shoppingModel.add(shoppingItem.toModel())
    }

    suspend fun removePayment(shoppingItem: ShoppingItem): Boolean {
        val transaction = dao.getTransactionByReference(shoppingItem.processResponseItem.reference)
        return dao.remove(transaction.transaction) > 0
    }
}