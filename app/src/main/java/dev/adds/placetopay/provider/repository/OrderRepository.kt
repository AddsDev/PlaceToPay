package dev.adds.placetopay.provider.repository

import dev.adds.placetopay.model.domain.CardModel
import dev.adds.placetopay.model.domain.InstrumentModel
import dev.adds.placetopay.model.domain.PayerModel
import dev.adds.placetopay.model.domain.PaymentModel
import dev.adds.placetopay.model.domain.payment.ProcessModel
import dev.adds.placetopay.provider.OrderProvider
import javax.inject.Inject


class OrderRepository @Inject constructor(
    private val orderProvider: OrderProvider
){

    fun newOrder(payerModel: PayerModel, paymentModel: PaymentModel, cardModel: CardModel){
        orderProvider.processModel = ProcessModel(payerModel, paymentModel, InstrumentModel(cardModel))
        orderProvider.orders.add(orderProvider.processModel!!)
    }
    fun getOrder(): ProcessModel{
        return  orderProvider.processModel!!
    }
    fun getAllOrders() = orderProvider.orders

}