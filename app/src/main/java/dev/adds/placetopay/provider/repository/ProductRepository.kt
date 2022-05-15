package dev.adds.placetopay.provider.repository

import dev.adds.placetopay.core.UrlApi
import dev.adds.placetopay.model.domain.Product
import dev.adds.placetopay.provider.ProductProvider
import dev.adds.placetopay.provider.services.ApiService

class ProductRepository {

    private val api = ApiService(api = UrlApi.PRODUCT)

    suspend fun getAllProducts() : List<Product>{
        val response = api.getProducts()
        ProductProvider.products = response
        return response
    }

}