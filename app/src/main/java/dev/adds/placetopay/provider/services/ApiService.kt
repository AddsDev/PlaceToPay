package dev.adds.placetopay.provider.services

import android.util.Log
import com.google.gson.Gson
import dev.adds.placetopay.model.domain.AmountModel
import dev.adds.placetopay.model.domain.ProductModel
import dev.adds.placetopay.model.domain.payment.ProcessResponseModel
import dev.adds.placetopay.model.domain.payment.StatusModel
import dev.adds.placetopay.model.domain.payment.ProcessModel
import dev.adds.placetopay.provider.services.products.IProductService
import dev.adds.placetopay.provider.services.wallets.IGatewayService
import dev.adds.placetopay.util.Constants
import dev.adds.placetopay.util.extension.apiFormat
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.io.IOException
import java.net.SocketTimeoutException
import java.util.*
import javax.inject.Inject

class ApiService @Inject constructor(
    private val apiProducts: IProductService,
    private val apiGateway: IGatewayService
) {


    suspend fun getProducts() : List<ProductModel>{
        return  withContext(Dispatchers.IO){
            val response = apiProducts.getAllProducts()
            response.body() ?: emptyList()
        }
    }

    suspend fun getProcessResponse(processModel: ProcessModel) : ProcessResponseModel{
        return  withContext(Dispatchers.IO){
            Log.i("RESPONSE_SER", Gson().toJson(processModel))
            val responseTransaction : ProcessResponseModel = ProcessResponseModel(
                StatusModel(Constants.StatusResponse.FAILED.status, String(),String()),
                processModel.paymentModel.amountModel)
            try {
                val response = apiGateway.getTransaction(processModel)
                if(response.code() == 200)
                    return@withContext response.body()!!
                else
                    return@withContext responseTransaction
            }catch (e: SocketTimeoutException){
                return@withContext responseTransaction.apply {
                    statusModel.status = Constants.StatusResponse.PROCESSING.status
                }
            }
            catch(e:  OutOfMemoryError) {
                return@withContext responseTransaction.apply {
                    statusModel.status = Constants.StatusResponse.PROCESSING.status
                }
            }
            catch(e: IOException) {
                return@withContext responseTransaction.apply {
                    statusModel.status = Constants.StatusResponse.PROCESSING.status
                }
            }
        }
    }
}