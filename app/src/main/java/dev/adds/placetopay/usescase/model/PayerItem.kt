package dev.adds.placetopay.usescase.model

import dev.adds.placetopay.model.database.entities.PayerEntity
import dev.adds.placetopay.model.domain.PayerModel
import dev.adds.placetopay.usescase.converts.IConvertModel

data class PayerItem(var name: String,
                     var surname : String,
                     var email: String,
                     var documentType: String,
                     var document: String,
                     var mobile: String) : IConvertModel<PayerModel> {
    override fun toModel(): PayerModel = PayerModel(name, surname, email, documentType, document, mobile)
}

fun PayerModel.toDomain() = PayerItem(name, surname, email, documentType, document, mobile)

fun PayerEntity.toDomain() = PayerItem(name, surname, email, documentType, document, mobile)