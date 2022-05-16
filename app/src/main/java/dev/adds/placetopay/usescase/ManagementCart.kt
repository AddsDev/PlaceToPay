package dev.adds.placetopay.usescase

import dev.adds.placetopay.model.domain.Product
import dev.adds.placetopay.provider.repository.CartRepository

class ManagementCart {

    private val repository = CartRepository()

    operator fun invoke(): List<Product>? = repository.getAllProducts()

    fun addProduct(product: Product): Unit = repository.addProduct(product)

    fun removeProduct(product: Product): Boolean = repository.removeProduct(product)

    fun clean(): Unit = repository.clean()
    fun computedTotal(): Float = repository.getTotal()

}