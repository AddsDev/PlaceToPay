package dev.adds.placetopay.model.domain

data class Instrument(var card: Card)

data class Card(var number: String,var expiration: String, var cvv : String, var installments: Int)