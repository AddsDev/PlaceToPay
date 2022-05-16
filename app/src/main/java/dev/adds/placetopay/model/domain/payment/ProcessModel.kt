package dev.adds.placetopay.model.domain.payment

import com.google.gson.annotations.SerializedName
import dev.adds.placetopay.model.domain.IModel
import dev.adds.placetopay.model.domain.InstrumentModel
import dev.adds.placetopay.model.domain.PayerModel
import dev.adds.placetopay.model.domain.PaymentModel
import dev.adds.placetopay.util.Constants

data class ProcessModel(
    @SerializedName("payer") var payerModel: PayerModel, @SerializedName("payment") var paymentModel: PaymentModel,
    @SerializedName("instrument") var instrumentModel: InstrumentModel, @SerializedName("ipAddress") var ipAddress: String = "127.0.0.1",
    @SerializedName("userAgent") var userAgent: String = "Testing") : IModel {
    @SerializedName("auth") var authModel: AuthModel = AuthModel(Constants.PLACE_TO_PAY_LOGIN, Constants.PLACE_TO_PAY_KEY)
}