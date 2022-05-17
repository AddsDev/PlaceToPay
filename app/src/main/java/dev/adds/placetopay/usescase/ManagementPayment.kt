package dev.adds.placetopay.usescase

import dev.adds.placetopay.provider.repository.PaymentRepository
import dev.adds.placetopay.usescase.crud.IAddItem
import dev.adds.placetopay.usescase.crud.IRemoveItem
import dev.adds.placetopay.usescase.model.ShoppingItem
import javax.inject.Inject

class ManagementPayment @Inject constructor(
    private val repository: PaymentRepository
    ): IAddItem<ShoppingItem>, IRemoveItem<ShoppingItem>{


    suspend operator fun invoke(): List<ShoppingItem> = repository.getAllPayments()

    override suspend fun addItem(item: ShoppingItem): Unit = repository.addPayment(item)

    override suspend fun removeItem(item: ShoppingItem): Boolean = repository.removePayment(item)

}