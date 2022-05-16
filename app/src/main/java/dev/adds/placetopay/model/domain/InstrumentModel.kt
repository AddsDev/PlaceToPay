package dev.adds.placetopay.model.domain

import com.google.gson.annotations.SerializedName

data class InstrumentModel(@SerializedName("card") var cardModel: CardModel) : IModel

data class CardModel(@SerializedName("number") var number: String,
                     @SerializedName("expiration") var expiration: String,
                     @SerializedName("cvv") var cvv : String,
                     @SerializedName("installments") var installments: Int) : IModel