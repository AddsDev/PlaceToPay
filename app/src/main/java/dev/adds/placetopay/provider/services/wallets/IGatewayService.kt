package dev.adds.placetopay.provider.services.wallets

import dev.adds.placetopay.model.domain.payment.ProcessResponse
import retrofit2.http.POST

import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.Headers

interface IGatewayService {

    /*@Headers("Content-Type: application/json")
    @POST("gateway/information")
    fun getInformation(@Body request: Request?): Call<Response>*/

    @Headers("Content-Type: application/json")
    @POST("gateway/process")
    fun getTransaction(@Body process: Process): Call<ProcessResponse>
}