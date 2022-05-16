package dev.adds.placetopay.usescase

import dev.adds.placetopay.model.domain.ProductModel
import dev.adds.placetopay.provider.repository.CartRepository
import dev.adds.placetopay.usescase.model.ProductItem
import dev.adds.placetopay.usescase.model.toDomain
import javax.inject.Inject

class GetProductsCart @Inject constructor(
    private val repository: CartRepository
){

    operator fun invoke(): List<ProductItem>? = repository.getAllProducts().map { it.toDomain() }

}