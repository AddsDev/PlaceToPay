package dev.adds.placetopay.provider

import dev.adds.placetopay.model.domain.payment.ProcessResponseModel
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class ProcessProvider @Inject constructor() {
    var process : MutableList<ProcessResponseModel> = mutableListOf()
}