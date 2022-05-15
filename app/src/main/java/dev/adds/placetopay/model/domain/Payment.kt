package dev.adds.placetopay.model.domain


data class Payment(var reference: String, var description: String, var amount: Amount)

data class Amount(var currency: String, var total: Int)