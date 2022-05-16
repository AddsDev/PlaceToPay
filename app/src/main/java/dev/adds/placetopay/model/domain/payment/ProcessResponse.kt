package dev.adds.placetopay.model.domain.payment

import dev.adds.placetopay.model.domain.Amount

data class ProcessResponse(var status: Status, var provider: String,var internalReference: Long,
                           var reference: String, var paymentMethod: String, var amount: Amount,
                           var authorization: String)
