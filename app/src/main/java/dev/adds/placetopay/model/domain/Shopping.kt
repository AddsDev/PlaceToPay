package dev.adds.placetopay.model.domain

import dev.adds.placetopay.model.domain.payment.ProcessResponse
import dev.adds.placetopay.model.domain.payment.Process

data class Shopping(var process: Process, var processResponse: ProcessResponse)