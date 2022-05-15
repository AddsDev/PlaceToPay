package dev.adds.placetopay.usescase

import dev.adds.placetopay.model.domain.Card
import dev.adds.placetopay.model.domain.Payer
import dev.adds.placetopay.model.domain.Payment
import dev.adds.placetopay.model.domain.payment.Process
import dev.adds.placetopay.provider.repository.OrderRepository

class ManagementOrder {

    private val respository = OrderRepository()

    operator fun invoke(): List<Process>? = respository.getAllOrders()

    fun newOrder(payer: Payer, payment: Payment, card: Card): Unit = respository.newOrder(payer, payment, card)

    fun getOrder(): Process = respository.getOrder()

}