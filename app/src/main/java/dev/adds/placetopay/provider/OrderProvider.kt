package dev.adds.placetopay.provider

import dev.adds.placetopay.model.domain.payment.ProcessModel
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class OrderProvider @Inject constructor() {
    var processModel: ProcessModel? = null
    var orders: MutableList<ProcessModel> = mutableListOf()
}