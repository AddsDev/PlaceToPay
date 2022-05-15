package dev.adds.placetopay.model.domain


data class Payment(var referece: String, var description: String, var amount: Amount)

data class Amount(var currency: String, var total: Int)