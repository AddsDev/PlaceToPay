package dev.adds.placetopay.provider.services

import dev.adds.placetopay.core.RetrofitHelper
import dev.adds.placetopay.core.UrlApi
import dev.adds.placetopay.model.domain.Amount
import dev.adds.placetopay.model.domain.Product
import dev.adds.placetopay.model.domain.payment.ProcessResponse
import dev.adds.placetopay.model.domain.payment.Status
import dev.adds.placetopay.model.domain.payment.Process
import dev.adds.placetopay.provider.services.products.IProductService
import dev.adds.placetopay.provider.services.wallets.IGatewayService
import dev.adds.placetopay.util.Constants
import dev.adds.placetopay.util.extension.apiFormat
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.util.*

class ApiService(api: UrlApi) {

    private  val retrofit = RetrofitHelper.getRetrofit(api)

    suspend fun getProducts() : List<Product>{
        return  withContext(Dispatchers.IO){
            val response = retrofit.create(IProductService::class.java).getAllProducts()
            response.body() ?: emptyList()
        }
    }

    suspend fun getProcessResponse(process: Process) : ProcessResponse{
        return  withContext(Dispatchers.IO){
            val response = retrofit.create(IGatewayService::class.java).getTransaction(process)
            if(response.code() == 200) return@withContext response.body()!!
            else return@withContext ProcessResponse(Status(Constants.StatusResponse.FAILED.name,response.errorBody()!!.string(),
                response.message(),Date().apiFormat()), "", 0, "", "", Amount("", 0),
                "")

        }
    }
}