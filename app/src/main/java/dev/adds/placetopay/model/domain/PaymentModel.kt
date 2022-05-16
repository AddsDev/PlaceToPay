package dev.adds.placetopay.model.domain

import com.google.gson.annotations.SerializedName


data class PaymentModel(@SerializedName("reference") var reference: String, @SerializedName("description") var description: String,
                        @SerializedName("amount") var amountModel: AmountModel) : IModel

data class AmountModel(@SerializedName("currency") var currency: String,
                       @SerializedName("total") var total: Int) : IModel