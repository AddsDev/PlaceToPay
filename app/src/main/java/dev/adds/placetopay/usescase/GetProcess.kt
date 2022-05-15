package dev.adds.placetopay.usescase

import dev.adds.placetopay.model.domain.payment.Process
import dev.adds.placetopay.model.domain.payment.ProcessResponse
import dev.adds.placetopay.provider.repository.PaymentRepository

class GetProcess {
    private val  respository = PaymentRepository()

    suspend operator fun invoke(process: Process): ProcessResponse = respository.getProcessResponse(process)
}