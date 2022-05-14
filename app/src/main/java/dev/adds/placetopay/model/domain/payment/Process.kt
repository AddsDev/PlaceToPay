package dev.adds.placetopay.model.domain.payment

import dev.adds.placetopay.model.domain.Card
import dev.adds.placetopay.model.domain.Payer
import dev.adds.placetopay.model.domain.Payment

data class Process(var payer: Payer, var payment: Payment, var card: Card)