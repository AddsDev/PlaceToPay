package dev.adds.placetopay.usescase.model

import dev.adds.placetopay.model.database.entities.StatusEntity
import dev.adds.placetopay.model.domain.payment.StatusModel
import dev.adds.placetopay.usescase.converters.IConvertModel

data class StatusItem(var status: String, var reason: String,
                      var message: String, var date: String): IConvertModel<StatusModel> {
    override fun toModel(): StatusModel = StatusModel(status, reason, message, date)
}

fun StatusModel.toDomain(): StatusItem = StatusItem(status, reason, message, date)
fun StatusEntity.toDomain(): StatusItem = StatusItem(status?: String(), reason?: String(), message?: String(), date?: String())