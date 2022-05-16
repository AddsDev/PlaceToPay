package dev.adds.placetopay.usescase.model

import dev.adds.placetopay.model.domain.ShoppingModel
import dev.adds.placetopay.usescase.converts.IConvertModel

data class ShoppingItem(
    var processItem: ProcessItem,
    var processResponseItem: ProcessResponseItem
): IConvertModel<ShoppingModel> {
    override fun toModel(): ShoppingModel = ShoppingModel(processItem.toModel(),
        processResponseItem.toModel())
}

fun ShoppingModel.toDomain() = ShoppingItem(processModel.toDomain(),
    processResponseModel.toDomain())