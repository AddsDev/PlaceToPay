package dev.adds.placetopay.model.domain.payment

import dev.adds.placetopay.model.domain.Instrument
import dev.adds.placetopay.model.domain.Payer
import dev.adds.placetopay.model.domain.Payment

data class Process(
    var payer: Payer, var payment: Payment, var instrument: Instrument, var ipAddress: String = "127.0.01",
    var userAgent: String = "Testing")