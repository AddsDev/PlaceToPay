package dev.adds.placetopay.provider

import dev.adds.placetopay.model.domain.Shopping

class PaymentProvider {
    companion object{
        var shopping: MutableList<Shopping> = mutableListOf()
    }
}