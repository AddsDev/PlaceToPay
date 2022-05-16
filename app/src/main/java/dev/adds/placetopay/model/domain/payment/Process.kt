package dev.adds.placetopay.model.domain.payment

import com.google.gson.annotations.SerializedName
import dev.adds.placetopay.model.domain.Instrument
import dev.adds.placetopay.model.domain.Payer
import dev.adds.placetopay.model.domain.Payment
import dev.adds.placetopay.util.Constants

data class Process(
    @SerializedName("payer") var payer: Payer, @SerializedName("payment") var payment: Payment,
    @SerializedName("instrument") var instrument: Instrument, @SerializedName("ipAddress") var ipAddress: String = "127.0.0.1",
    @SerializedName("userAgent") var userAgent: String = "Testing"){
    @SerializedName("auth") var auth: Auth = Auth(Constants.PLACE_TO_PAY_LOGIN, Constants.PLACE_TO_PAY_KEY)
}