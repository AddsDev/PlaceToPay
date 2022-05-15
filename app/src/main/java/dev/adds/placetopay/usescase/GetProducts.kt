package dev.adds.placetopay.usescase

import dev.adds.placetopay.model.domain.Product
import dev.adds.placetopay.provider.repository.ProductRepository

class GetProducts {
    private val respository = ProductRepository()

    suspend operator fun invoke(): List<Product>? = respository.getAllProducts()

}