package dev.adds.placetopay.usescase

import dev.adds.placetopay.model.domain.Product
import dev.adds.placetopay.provider.repository.CartRepository
import dev.adds.placetopay.provider.repository.ProductRepository
import javax.inject.Inject

class GetProductsCart @Inject constructor(
    private val repository: CartRepository
){

    operator fun invoke(): List<Product>? = repository.getAllProducts()

}