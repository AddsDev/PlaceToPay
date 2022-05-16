package dev.adds.placetopay.usescase.model

import dev.adds.placetopay.model.database.entities.AmountEntity
import dev.adds.placetopay.model.database.entities.CardEntity
import dev.adds.placetopay.model.database.entities.TransactionEntity
import dev.adds.placetopay.model.domain.payment.ProcessModel
import dev.adds.placetopay.usescase.converts.IConvertModel

data class ProcessItem(var payerItem: PayerItem, var paymentItem: PaymentItem,
                       var instrumentItem: InstrumentItem): IConvertModel<ProcessModel>{

    override fun toModel(): ProcessModel = ProcessModel(payerItem.toModel(),
        paymentItem.toModel(), instrumentItem.toModel())
}

fun ProcessModel.toDomain() = ProcessItem(payerModel.toDomain(),
    paymentModel.toDomain(),
    instrumentModel.toDomain())

fun TransactionEntity.toProcessDomain() = ProcessItem(payerEntity.toDomain(),
    PaymentItem(reference,"", amountEntity.toDomain()),
    InstrumentItem(cardEntity.toDomain())
)