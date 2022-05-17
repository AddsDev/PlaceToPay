package dev.adds.placetopay.provider.repository

import dev.adds.placetopay.model.domain.ProductModel
import dev.adds.placetopay.provider.ProductProvider
import dev.adds.placetopay.provider.services.ApiService
import javax.inject.Inject

class ProductRepository @Inject constructor(
    private val api: ApiService,
    private val productProvider: ProductProvider
){
    suspend fun getAllProducts() : List<ProductModel>{
        val response = api.getProducts()
        productProvider.productModels = response
        return response
    }

}