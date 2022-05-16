package dev.adds.placetopay.model.domain

import dev.adds.placetopay.model.domain.payment.ProcessResponse

data class Shopping(var process: Process, var processResponse: ProcessResponse, var payer: Payer, var card: Card)