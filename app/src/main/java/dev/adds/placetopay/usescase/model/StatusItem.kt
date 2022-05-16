package dev.adds.placetopay.usescase.model

import dev.adds.placetopay.model.domain.payment.StatusModel
import dev.adds.placetopay.usescase.converts.IConvertModel

data class StatusItem(var status: String, var reason: String,
                      var message: String, var date: String): IConvertModel<StatusModel> {
    override fun toModel(): StatusModel = StatusModel(status, reason, message, date)
}

fun StatusModel.toDomain() = StatusItem(status, reason, message, date)