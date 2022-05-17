package dev.adds.placetopay.provider.services.products

import dev.adds.placetopay.model.domain.ProductModel
import retrofit2.Response
import retrofit2.http.GET

interface IProductService {

    @GET("products")
    suspend fun getAllProducts() : Response<List<ProductModel>>
}