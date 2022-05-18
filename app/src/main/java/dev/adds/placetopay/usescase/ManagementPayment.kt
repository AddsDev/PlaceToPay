package dev.adds.placetopay.usescase

import android.content.Context
import androidx.work.Operation
import dagger.hilt.android.qualifiers.ApplicationContext
import dev.adds.placetopay.provider.repository.PaymentRepository
import dev.adds.placetopay.usescase.crud.IAddItem
import dev.adds.placetopay.usescase.crud.IRemoveItem
import dev.adds.placetopay.usescase.model.ShoppingItem
import javax.inject.Inject

class ManagementPayment @Inject constructor(
    private val repository: PaymentRepository,
    private val managementTransactions: ManagementTransactions
    ): IAddItem<ShoppingItem>, IRemoveItem<ShoppingItem>{


    suspend operator fun invoke(): List<ShoppingItem> {
        ManagementTransactions.pendingList = repository.getAllPendingPayments()
        ManagementTransactions.repository = repository
        managementTransactions.validatePendingTransaction()
        return repository.getAllPayments()
    }

    override suspend fun addItem(item: ShoppingItem): Unit = repository.addPayment(item)

    override suspend fun removeItem(item: ShoppingItem): Boolean = repository.removePayment(item)

}