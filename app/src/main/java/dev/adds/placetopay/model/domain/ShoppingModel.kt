package dev.adds.placetopay.model.domain

import com.google.gson.annotations.SerializedName
import dev.adds.placetopay.model.domain.payment.ProcessResponseModel
import dev.adds.placetopay.model.domain.payment.ProcessModel

data class ShoppingModel(@SerializedName("process")var processModel: ProcessModel,
                         @SerializedName("processResponse") var processResponseModel: ProcessResponseModel)  : IModel