package dev.adds.placetopay.model.domain.payment

import com.google.gson.annotations.SerializedName
import dev.adds.placetopay.model.domain.AmountModel
import dev.adds.placetopay.model.domain.IModel

data class ProcessResponseModel(
    @SerializedName("status") var statusModel: StatusModel,
    @SerializedName("provider") var provider: String,
    @SerializedName("internalReference") var internalReference: Long,
    @SerializedName("reference") var reference: String,
    @SerializedName("paymentMethod") var paymentMethod: String,
    @SerializedName("amount") var amountModel: AmountModel,
    @SerializedName("authorization") var authorization: String)  : IModel {
    constructor(statusModel: StatusModel): this(statusModel, String(), 0,
        String(), String(), AmountModel(String(),0), String())
}
