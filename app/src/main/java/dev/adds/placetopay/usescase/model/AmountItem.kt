package dev.adds.placetopay.usescase.model

import dev.adds.placetopay.model.database.entities.AmountEntity
import dev.adds.placetopay.model.domain.AmountModel
import dev.adds.placetopay.usescase.converts.IConvertModel


data class AmountItem(var currency: String,
                      var total: Int): IConvertModel<AmountModel>{
    override fun toModel(): AmountModel = AmountModel(currency, total)
}

fun AmountModel.toDomain() = AmountItem(currency, total)
fun AmountEntity.toDomain() = AmountItem(currency, total)