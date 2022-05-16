package dev.adds.placetopay.usescase

import dev.adds.placetopay.model.domain.ShoppingModel
import dev.adds.placetopay.provider.repository.PaymentRepository
import dev.adds.placetopay.usescase.model.ShoppingItem
import javax.inject.Inject

class ManagementPayment @Inject constructor(
    private val repository: PaymentRepository
    ){


    operator fun invoke(): List<ShoppingItem> = repository.getAllPayments()

    fun addPayment(shoppingItem: ShoppingItem): Unit = repository.addPayment(shoppingItem)

    fun removePayment(shoppingItem: ShoppingItem): Boolean = repository.removePayment(shoppingItem)

}