package dev.adds.placetopay.model.domain.payment

import com.google.gson.annotations.SerializedName
import dev.adds.placetopay.model.domain.IModel

data class ReferenceResponseModel(
    @SerializedName("status") var statusModel: StatusModel,
    @SerializedName("provider") var provider: String,
    @SerializedName("reference") var reference: String,
    @SerializedName("authorization") var authorization: String
): IModel{

    constructor(status: StatusModel):this(status, String(), String(), String())
}