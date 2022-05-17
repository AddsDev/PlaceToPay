package dev.adds.placetopay.usescase

import dev.adds.placetopay.model.domain.CardModel
import dev.adds.placetopay.model.domain.PayerModel
import dev.adds.placetopay.model.domain.PaymentModel
import dev.adds.placetopay.model.domain.payment.ProcessModel
import dev.adds.placetopay.provider.repository.OrderRepository
import dev.adds.placetopay.usescase.crud.IGetItem
import dev.adds.placetopay.usescase.model.*
import javax.inject.Inject

class ManagementOrder @Inject constructor(
    private val repository: OrderRepository
) : IGetItem<ProcessItem>{

    operator fun invoke(): List<ProcessItem> = repository.getAllOrders().map { it.toDomain() }

    fun addOrder(payerItem: PayerItem, paymentItem: PaymentItem, cardItem: CardItem): Unit =
        repository.newOrder(payerItem.toModel(), paymentItem.toModel(), cardItem.toModel())

    override fun getItem(): ProcessItem = repository.getOrder().toDomain()

}