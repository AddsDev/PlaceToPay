package dev.adds.placetopay.provider

import dev.adds.placetopay.model.domain.ShoppingModel
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class PaymentProvider @Inject constructor() {
    var shoppingModel: MutableList<ShoppingModel> = mutableListOf()
}