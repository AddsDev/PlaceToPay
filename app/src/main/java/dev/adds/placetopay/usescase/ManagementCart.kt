package dev.adds.placetopay.usescase

import dev.adds.placetopay.model.domain.ProductModel
import dev.adds.placetopay.provider.repository.CartRepository
import dev.adds.placetopay.usescase.model.ProductItem
import dev.adds.placetopay.usescase.model.toDomain
import javax.inject.Inject

class ManagementCart @Inject constructor(
    private val repository: CartRepository
    ) {

    operator fun invoke(): List<ProductItem>? = repository.getAllProducts().map { it.toDomain() }

    fun addProduct(productItem: ProductItem): Unit = repository.addProduct(productItem.toModel())

    fun removeProduct(productItem: ProductItem): Boolean = repository.removeProduct(productItem.toModel())

    fun clean(): Unit = repository.clean()

    fun computedTotal(): Float = repository.getTotal()

}