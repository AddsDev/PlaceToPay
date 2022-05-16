package dev.adds.placetopay.model.domain

import com.google.gson.annotations.SerializedName


data class Payment(@SerializedName("reference") var reference: String, @SerializedName("description") var description: String,
                   @SerializedName("amount") var amount: Amount)

data class Amount(@SerializedName("currency") var currency: String, @SerializedName("total") var total: Int)