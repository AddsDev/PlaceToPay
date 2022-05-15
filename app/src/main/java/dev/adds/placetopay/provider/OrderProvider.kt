package dev.adds.placetopay.provider

import dev.adds.placetopay.model.domain.Instrument
import dev.adds.placetopay.model.domain.Payer
import dev.adds.placetopay.model.domain.Payment
import dev.adds.placetopay.model.domain.payment.Process

class OrderProvider {
    companion object{
        var process: Process? = null
        var orders: MutableList<Process>? = mutableListOf()
    }
}