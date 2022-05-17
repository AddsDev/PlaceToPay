package dev.adds.placetopay.model.domain.payment

import com.google.gson.annotations.SerializedName
import dev.adds.placetopay.model.domain.IModel
import dev.adds.placetopay.util.Constants

data class ReferenceModel(@SerializedName("internalReference") var internalReference: Long,
                          @SerializedName("auth") var authModel: AuthModel =
        AuthModel(Constants.PLACE_TO_PAY_LOGIN, Constants.PLACE_TO_PAY_KEY)
) : IModel{
}