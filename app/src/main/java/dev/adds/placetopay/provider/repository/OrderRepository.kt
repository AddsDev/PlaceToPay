package dev.adds.placetopay.provider.repository

import dev.adds.placetopay.model.domain.Card
import dev.adds.placetopay.model.domain.Instrument
import dev.adds.placetopay.model.domain.Payer
import dev.adds.placetopay.model.domain.Payment
import dev.adds.placetopay.model.domain.payment.Process
import dev.adds.placetopay.provider.OrderProvider


class OrderRepository {

    fun newOrder(payer: Payer, payment: Payment, card: Card){
        OrderProvider.process = Process(payer, payment, Instrument(card))
        OrderProvider.orders.add(OrderProvider.process!!)
    }
    fun getOrder(): Process{
        return  OrderProvider.process!!
    }
    fun getAllOrders() = OrderProvider.orders

}