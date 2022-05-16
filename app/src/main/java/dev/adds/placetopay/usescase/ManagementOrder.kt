package dev.adds.placetopay.usescase

import dev.adds.placetopay.model.domain.Card
import dev.adds.placetopay.model.domain.Payer
import dev.adds.placetopay.model.domain.Payment
import dev.adds.placetopay.model.domain.payment.Process
import dev.adds.placetopay.provider.repository.OrderRepository
import javax.inject.Inject

class ManagementOrder @Inject constructor(
    private val repository: OrderRepository
) {

    operator fun invoke(): List<Process>? = repository.getAllOrders()

    fun newOrder(payer: Payer, payment: Payment, card: Card): Unit = repository.newOrder(payer, payment, card)

    fun getOrder(): Process = repository.getOrder()

}