package dev.adds.placetopay.model.domain

import com.google.gson.annotations.SerializedName

data class Instrument(@SerializedName("card") var card: Card)

data class Card(@SerializedName("number") var number: String,@SerializedName("expiration") var expiration: String,
                @SerializedName("cvv") var cvv : String, @SerializedName("installments") var installments: Int)