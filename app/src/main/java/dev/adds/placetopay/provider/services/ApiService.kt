package dev.adds.placetopay.provider.services

import android.util.Log
import com.google.gson.Gson
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
import javax.inject.Inject

class ApiService @Inject constructor(
    private val apiProducts: IProductService,
    private val apiGateway: IGatewayService
) {


    suspend fun getProducts() : List<Product>{
        return  withContext(Dispatchers.IO){
            val response = apiProducts.getAllProducts()
            response.body() ?: emptyList()
        }
    }

    suspend fun getProcessResponse(process: Process) : ProcessResponse{
        return  withContext(Dispatchers.IO){
            Log.i("RESPONSE_SER", Gson().toJson(process))
            val response = apiGateway.getTransaction(process)
            if(response.code() == 200) return@withContext response.body()!!
            else return@withContext ProcessResponse(Status(Constants.StatusResponse.FAILED.status,response.errorBody()!!.string(),
                response.message(),Date().apiFormat()), "", 0, "", "", Amount("", 0),
                "")

        }
    }
}