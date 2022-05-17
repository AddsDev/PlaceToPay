package dev.adds.placetopay.usescase.model

import dev.adds.placetopay.model.database.entities.CardEntity
import dev.adds.placetopay.model.domain.CardModel
import dev.adds.placetopay.model.domain.InstrumentModel
import dev.adds.placetopay.usescase.converters.IConvertModel

data class InstrumentItem (var cardItem: CardItem) : IConvertModel<InstrumentModel> {
    override fun toModel(): InstrumentModel =
        InstrumentModel(cardItem.toModel())

}

data class CardItem(var number: String,
                    var expiration: String,
                    var cvv : String,
                    var installments: Int) : IConvertModel<CardModel>{
    override fun toModel(): CardModel =
        CardModel(number, expiration, cvv, installments)

}
fun InstrumentModel.toDomain() = InstrumentItem(cardModel.toDomain())
fun CardModel.toDomain() = CardItem(number, expiration, cvv, installments)
fun CardEntity.toDomain() = CardItem(number, expiration, "", 0)