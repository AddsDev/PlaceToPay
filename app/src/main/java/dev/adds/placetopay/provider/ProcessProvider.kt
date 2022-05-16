package dev.adds.placetopay.provider

import dev.adds.placetopay.model.domain.payment.ProcessResponse

class ProcessProvider {
    companion object{
        var process : MutableList<ProcessResponse> = mutableListOf()
    }
}