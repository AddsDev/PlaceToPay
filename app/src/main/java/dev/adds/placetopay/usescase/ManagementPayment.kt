package dev.adds.placetopay.usescase

import dev.adds.placetopay.model.domain.Shopping
import dev.adds.placetopay.provider.repository.PaymentRepository

class ManagementPayment {
    private val  repository = PaymentRepository()


    operator fun invoke(): List<Shopping> = repository.getAllPayments()

    fun addPayment(shopping: Shopping): Unit = repository.addPayment(shopping)

    fun removePayment(shopping: Shopping): Boolean = repository.removePayment(shopping)

}