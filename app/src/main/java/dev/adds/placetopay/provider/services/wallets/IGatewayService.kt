package dev.adds.placetopay.provider.services.wallets

import dev.adds.placetopay.model.domain.payment.Request
import retrofit2.http.POST
import dev.adds.placetopay.model.domain.payment.RequestTransaction
import dev.adds.placetopay.model.domain.payment.Response
import dev.adds.placetopay.model.domain.payment.ResponseTransaction
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.Headers

interface IGatewayService {

    @Headers("Content-Type: application/json")
    @POST("gateway/information")
    fun getInformation(@Body request: Request?): Call<Response>

    @Headers("Content-Type: application/json")
    @POST("gateway/information")
    fun getTransaction(@Body requestTransaction: RequestTransaction): Call<ResponseTransaction?>
}