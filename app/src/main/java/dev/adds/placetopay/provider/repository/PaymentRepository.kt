package dev.adds.placetopay.provider.repository

import android.util.Log
import com.google.gson.Gson
import dev.adds.placetopay.core.UrlApi
import dev.adds.placetopay.model.domain.Shopping
import dev.adds.placetopay.model.domain.payment.Process
import dev.adds.placetopay.model.domain.payment.ProcessResponse
import dev.adds.placetopay.provider.PaymentProvider
import dev.adds.placetopay.provider.ProcessProvider
import dev.adds.placetopay.provider.services.ApiService
import javax.inject.Inject

class PaymentRepository @Inject constructor(
    private val api: ApiService,
    private val paymentProvider: PaymentProvider,
    private val processProvider: ProcessProvider
) {

    suspend fun getProcessResponse(process: Process) : ProcessResponse{
        Log.i("RESPONSE_REPOP", Gson().toJson(process))
        val response = api.getProcessResponse(process)
        processProvider.process.add(response)
        return response
    }
    fun getAllPayments(): List<Shopping>{
        return paymentProvider.shopping
    }
    fun addPayment(shopping: Shopping){
        paymentProvider.shopping.add(shopping)
    }
    fun removePayment(shopping: Shopping): Boolean = paymentProvider.shopping.remove(shopping)
}