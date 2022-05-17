package dev.adds.placetopay.provider.services

import android.util.Log
import dev.adds.placetopay.model.domain.ProductModel
import dev.adds.placetopay.model.domain.payment.*
import dev.adds.placetopay.provider.services.products.IProductService
import dev.adds.placetopay.provider.services.wallets.IGatewayService
import dev.adds.placetopay.util.Constants
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.io.IOException
import java.net.SocketTimeoutException
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
    suspend fun getQueryResponse(referenceModel: ReferenceModel) : ReferenceResponseModel{
        return  withContext(Dispatchers.IO){
            val infoTransaction : ReferenceResponseModel = ReferenceResponseModel(
                StatusModel(Constants.StatusResponse.FAILED.status, String(),String())
            )
            try {
                val response = apiGateway.getInfoTransaction(referenceModel)
                if(response.code() == 200)
                    return@withContext response.body()!!
                else{
                    Log.i(Constants.DEBUG_KEY, response.errorBody()!!.toString())
                    return@withContext infoTransaction
                }
            }catch (e: SocketTimeoutException){
                return@withContext infoTransaction.apply {
                    statusModel.status = Constants.StatusResponse.PROCESSING.status
                    Log.i(Constants.DEBUG_KEY, e.message!!)
                }
            }
            catch(e:  OutOfMemoryError) {
                return@withContext infoTransaction.apply {
                    statusModel.status = Constants.StatusResponse.PROCESSING.status
                    Log.i(Constants.DEBUG_KEY, e.message!!)
                }
            }
            catch(e: IOException) {
                return@withContext infoTransaction.apply {
                    statusModel.status = Constants.StatusResponse.PROCESSING.status
                    Log.i(Constants.DEBUG_KEY, e.message!!)
                }
            }
        }
    }
}