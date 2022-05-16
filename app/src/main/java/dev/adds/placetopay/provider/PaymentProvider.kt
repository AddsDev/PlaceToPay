package dev.adds.placetopay.provider

import dev.adds.placetopay.model.domain.Shopping
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class PaymentProvider @Inject constructor() {
    var shopping: MutableList<Shopping> = mutableListOf()
}