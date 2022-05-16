package dev.adds.placetopay.usescase

import dev.adds.placetopay.provider.repository.PaymentRepository
import dev.adds.placetopay.usescase.model.ProcessResponseItem
import dev.adds.placetopay.usescase.model.ProcessItem
import javax.inject.Inject

class GetProcess @Inject constructor(
    private val  repository: PaymentRepository
) {

    suspend operator fun invoke(process: ProcessItem): ProcessResponseItem =
        repository.getProcessResponse(process)
}