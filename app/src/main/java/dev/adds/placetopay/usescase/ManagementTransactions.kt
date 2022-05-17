package dev.adds.placetopay.usescase

import dev.adds.placetopay.provider.repository.PaymentRepository
import dev.adds.placetopay.usescase.model.ShoppingItem
import javax.inject.Inject

class ManagementTransactions @Inject constructor(
    private val paymentRepository: PaymentRepository,
    private val managementWorker: ManagementWorker
) {
    companion object{
        var pendingList: MutableList<ShoppingItem> = mutableListOf()
        var repository: PaymentRepository? = null
        suspend fun tryTransaction(shoppingItem: ShoppingItem) = repository?.tryTransaction(shoppingItem)
    }
    suspend fun validatePendingTransaction(){
        paymentRepository.getAllPendingPayments()
        managementWorker.initWorker()
    }


}