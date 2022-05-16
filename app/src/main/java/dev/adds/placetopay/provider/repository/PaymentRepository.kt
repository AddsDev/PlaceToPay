package dev.adds.placetopay.provider.repository

import dev.adds.placetopay.model.database.dao.TransactionDao
import dev.adds.placetopay.model.database.entities.TransactionEntity
import dev.adds.placetopay.model.domain.payment.ProcessModel
import dev.adds.placetopay.provider.PaymentProvider
import dev.adds.placetopay.provider.ProcessProvider
import dev.adds.placetopay.provider.services.ApiService
import dev.adds.placetopay.usescase.model.ShoppingItem
import dev.adds.placetopay.usescase.model.ProcessItem
import dev.adds.placetopay.usescase.model.ProcessResponseItem
import dev.adds.placetopay.usescase.model.toDomain
import javax.inject.Inject

class PaymentRepository @Inject constructor(
    private val api: ApiService,
    private val paymentProvider: PaymentProvider,
    private val processProvider: ProcessProvider,
    private val transactionDao: TransactionDao
) {

    suspend fun getProcessResponse(processItem: ProcessItem) : ProcessResponseItem {
        val response = api.getProcessResponse(processItem.toModel())// pasar modelo
        //transactionDao.insert(TransactionEntity(null, response.statusModel))
        processProvider.process.add(response)

        return response.toDomain()
    }
    fun getAllPayments(): List<ShoppingItem>{
        return paymentProvider.shoppingModel.map { it.toDomain() }
    }

    fun addPayment(shoppingItem: ShoppingItem){
        paymentProvider.shoppingModel.add(shoppingItem.toModel())
    }

    fun removePayment(shoppingItem: ShoppingItem): Boolean =
        paymentProvider.shoppingModel.remove(shoppingItem.toModel())
}