package dev.adds.placetopay.usescase.model

import dev.adds.placetopay.model.domain.payment.ProcessResponseModel
import dev.adds.placetopay.usescase.converters.IConvertModel

data class ProcessResponseItem(
    var statusItem: StatusItem, var provider: String,
    var internalReference: Long, var reference: String,
    var paymentMethod: String, var amountItem: AmountItem,
    var authorization: String
): IConvertModel<ProcessResponseModel> {
    override fun toModel(): ProcessResponseModel  = ProcessResponseModel(statusItem.toModel(),
        provider, internalReference, reference,
        paymentMethod, amountItem.toModel(), authorization)
}

fun ProcessResponseModel.toDomain() = ProcessResponseItem(statusModel.toDomain(), provider,
    internalReference, reference, paymentMethod, amountModel.toDomain(), authorization)