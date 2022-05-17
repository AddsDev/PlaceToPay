package dev.adds.placetopay.usescase.model

import dev.adds.placetopay.model.database.entities.TransactionDetailEntity
import dev.adds.placetopay.model.domain.ShoppingModel
import dev.adds.placetopay.usescase.converters.IConvertModel

data class ShoppingItem(
    var processItem: ProcessItem,
    var processResponseItem: ProcessResponseItem
): IConvertModel<ShoppingModel> {
    override fun toModel(): ShoppingModel = ShoppingModel(processItem.toModel(),
        processResponseItem.toModel())
}

fun ShoppingModel.toDomain() = ShoppingItem(processModel.toDomain(),
    processResponseModel.toDomain())

fun TransactionDetailEntity.toDomain(): ShoppingItem = ShoppingItem(
    ProcessItem(payer.toDomain(),
        PaymentItem(transaction.reference?: String(),
            transaction.provider?: String(),
            amount.toDomain()),
        InstrumentItem(card.toDomain())
    ),
    ProcessResponseItem(status.toDomain(),
        transaction.provider?: String(),
        transaction.transactionId,
        transaction.reference?: String(),
        String(),
        amount.toDomain(),
        String())
)