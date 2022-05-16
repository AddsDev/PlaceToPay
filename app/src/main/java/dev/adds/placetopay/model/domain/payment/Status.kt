package dev.adds.placetopay.model.domain.payment

import com.google.gson.annotations.SerializedName

data class Status(@SerializedName("status") var status: String, @SerializedName("reason") var reason: String,
                  @SerializedName("message") var message: String, @SerializedName("date") var date: String)