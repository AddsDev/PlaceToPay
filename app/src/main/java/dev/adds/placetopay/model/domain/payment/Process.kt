package dev.adds.placetopay.model.domain.payment

import dev.adds.placetopay.model.domain.Instrument
import dev.adds.placetopay.model.domain.Payer
import dev.adds.placetopay.model.domain.Payment
import dev.adds.placetopay.util.Constants

data class Process(
    var payer: Payer, var payment: Payment, var instrument: Instrument, var ipAddress: String = "127.0.01",
    var userAgent: String = "Testing"){
    var auth: Auth = Auth(Constants.PLACE_TO_PAY_LOGIN, Constants.PLACE_TO_PAY_KEY)
}