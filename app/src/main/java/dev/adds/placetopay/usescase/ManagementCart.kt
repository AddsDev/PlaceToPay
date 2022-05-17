package dev.adds.placetopay.usescase

import dev.adds.placetopay.provider.repository.CartRepository
import dev.adds.placetopay.usescase.crud.IAddItem
import dev.adds.placetopay.usescase.crud.IGetItems
import dev.adds.placetopay.usescase.crud.IRemoveItem
import dev.adds.placetopay.usescase.model.ProductItem
import javax.inject.Inject

class ManagementCart @Inject constructor(
    private val repository: CartRepository
    ): IGetItems<ProductItem>,IAddItem<ProductItem>, IRemoveItem<ProductItem>{

    suspend operator fun invoke(): List<ProductItem> = repository.getAllProductsCart()

    override suspend fun removeItem(item: ProductItem): Boolean = repository.removeProduct(item)

    suspend fun clean(): Unit = repository.clean()

    fun computedTotal(): Float = repository.getTotal()

    override fun getAllItems(): List<ProductItem> = repository.getAllProducts()

    override suspend fun addItem(item: ProductItem): Unit = repository.addProduct(item)

}