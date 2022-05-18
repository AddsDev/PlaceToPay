package dev.adds.placetopay.model.domain.payment

import com.google.gson.annotations.SerializedName
import dev.adds.placetopay.model.domain.IModel

data class StatusModel(@SerializedName("status") var status: String,
                       @SerializedName("reason") var reason: String,
                       @SerializedName("message") var message: String,
                       @SerializedName("date") var date: String) : IModel {
    constructor(status: String, reason: String, message: String) : this(status, reason, message, String()) {

    }
}