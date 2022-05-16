package dev.adds.placetopay.usescase

import dev.adds.placetopay.model.domain.Product
import dev.adds.placetopay.provider.repository.ProductRepository
import javax.inject.Inject

class GetProducts @Inject constructor(
    private val repository: ProductRepository
){
    suspend operator fun invoke(): List<Product> = repository.getAllProducts()
}