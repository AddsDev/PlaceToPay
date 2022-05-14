package dev.adds.placetopay.model.domain

import java.util.*

data class Payment(var referece: String, var description: String)

data class Amount(var currency: String, var total: Int)