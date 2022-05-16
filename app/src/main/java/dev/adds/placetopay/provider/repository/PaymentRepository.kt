package dev.adds.placetopay.provider.repository

import dev.adds.placetopay.core.UrlApi
import dev.adds.placetopay.model.domain.Shopping
import dev.adds.placetopay.model.domain.payment.Process
import dev.adds.placetopay.model.domain.payment.ProcessResponse
import dev.adds.placetopay.provider.PaymentProvider
import dev.adds.placetopay.provider.ProcessProvider
import dev.adds.placetopay.provider.services.ApiService

class PaymentRepository {
    private val api = ApiService(UrlApi.PLACE_TO_PAY)
    suspend fun getProcessResponse(process: Process) : ProcessResponse{
        val response = api.getProcessResponse(process)
        ProcessProvider.process.add(response)
        return response
    }
    fun getAllPayments(): List<Shopping>{
        return PaymentProvider.shopping
    }
    fun addPayment(shopping: Shopping){
        PaymentProvider.shopping.add(shopping)
    }
    fun removePayment(shopping: Shopping): Boolean = PaymentProvider.shopping.remove(shopping)
}