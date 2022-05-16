package dev.adds.placetopay.provider

import dev.adds.placetopay.model.domain.payment.Process
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class OrderProvider @Inject constructor() {
    var process: Process? = null
    var orders: MutableList<Process> = mutableListOf()
}