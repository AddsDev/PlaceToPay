package dev.adds.placetopay.provider.services.products

import dev.adds.placetopay.core.RetrofitHelper
import dev.adds.placetopay.core.UrlApi
import dev.adds.placetopay.model.domain.Product
import dev.adds.placetopay.provider.ProductProvider
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class ProductService(api: UrlApi) {

    private  val retrofit = RetrofitHelper.getRetrofit(api)

    suspend fun getProducts() : List<Product>{
        return  withContext(Dispatchers.IO){
            val response = retrofit.create(IProductService::class.java).getAllProducts()
            response.body() ?: emptyList()
        }
    }
}