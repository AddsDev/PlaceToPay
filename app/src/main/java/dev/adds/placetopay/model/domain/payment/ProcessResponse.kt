package dev.adds.placetopay.model.domain.payment

import com.google.gson.annotations.SerializedName
import dev.adds.placetopay.model.domain.Amount

data class ProcessResponse(@SerializedName("status") var status: Status, @SerializedName("provider") var provider: String,
                           @SerializedName("internalReference") var internalReference: Long, @SerializedName("reference") var reference: String,
                           @SerializedName("paymentMethod") var paymentMethod: String, @SerializedName("amount") var amount: Amount,
                           @SerializedName("authorization") var authorization: String)
