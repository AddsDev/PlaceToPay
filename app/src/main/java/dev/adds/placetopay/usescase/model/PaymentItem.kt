package dev.adds.placetopay.usescase.model

import dev.adds.placetopay.model.domain.PaymentModel
import dev.adds.placetopay.usescase.converts.IConvertModel


data class PaymentItem(var reference: String,
                       var description: String,
                       var amountItem: AmountItem): IConvertModel<PaymentModel> {
    override fun toModel(): PaymentModel = PaymentModel(reference, description, amountItem.toModel())
}

fun PaymentModel.toDomain() = PaymentItem(reference, description, amountModel.toDomain())