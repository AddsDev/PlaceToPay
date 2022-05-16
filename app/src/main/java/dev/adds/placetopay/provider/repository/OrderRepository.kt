package dev.adds.placetopay.provider.repository

import dev.adds.placetopay.model.domain.Card
import dev.adds.placetopay.model.domain.Instrument
import dev.adds.placetopay.model.domain.Payer
import dev.adds.placetopay.model.domain.Payment
import dev.adds.placetopay.model.domain.payment.Process
import dev.adds.placetopay.provider.OrderProvider
import javax.inject.Inject


class OrderRepository @Inject constructor(
    private val orderProvider: OrderProvider
){

    fun newOrder(payer: Payer, payment: Payment, card: Card){
        orderProvider.process = Process(payer, payment, Instrument(card))
        orderProvider.orders.add(orderProvider.process!!)
    }
    fun getOrder(): Process{
        return  orderProvider.process!!
    }
    fun getAllOrders() = orderProvider.orders

}