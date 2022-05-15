package dev.adds.placetopay.provider

import dev.adds.placetopay.model.domain.payment.Process

class OrderProvider {
    companion object{
        var process: Process? = null
        var orders: MutableList<Process> = mutableListOf()
    }
}