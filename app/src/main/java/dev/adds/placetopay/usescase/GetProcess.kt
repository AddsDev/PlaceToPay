package dev.adds.placetopay.usescase

import android.util.Log
import dev.adds.placetopay.provider.repository.PaymentRepository
import dev.adds.placetopay.usescase.model.ProcessResponseItem
import dev.adds.placetopay.usescase.model.ProcessItem
import javax.inject.Inject

class GetProcess @Inject constructor(
    private val  repository: PaymentRepository
) {

    suspend operator fun invoke(process: ProcessItem): ProcessResponseItem {
        return repository.getProcessResponse(process).apply {
            val transactionId = repository.register(this)
            repository.register(process.paymentItem.amountItem, transactionId)
            repository.register(process.instrumentItem.cardItem, transactionId)
            repository.register(process.payerItem, transactionId)
            repository.register(this.statusItem, transactionId)
        }
    }

}