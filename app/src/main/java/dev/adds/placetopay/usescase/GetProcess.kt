package dev.adds.placetopay.usescase

import dev.adds.placetopay.model.domain.payment.Process
import dev.adds.placetopay.model.domain.payment.ProcessResponse
import dev.adds.placetopay.provider.repository.PaymentRepository
import javax.inject.Inject

class GetProcess @Inject constructor(
    private val  repository: PaymentRepository
) {

    suspend operator fun invoke(process: Process): ProcessResponse = repository.getProcessResponse(process)
}