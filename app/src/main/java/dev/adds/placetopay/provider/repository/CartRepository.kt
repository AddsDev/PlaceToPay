package dev.adds.placetopay.provider.repository

import android.util.Log
import dev.adds.placetopay.model.database.dao.CartDao
import dev.adds.placetopay.model.database.entities.CartEntity
import dev.adds.placetopay.model.domain.ProductModel
import dev.adds.placetopay.provider.ProductProvider
import dev.adds.placetopay.usescase.model.ProductItem
import dev.adds.placetopay.usescase.model.toDomain
import javax.inject.Inject

class CartRepository @Inject constructor(
    private val productProvider: ProductProvider,
    private val dao: CartDao
){

    fun getAllProducts(): List<ProductItem>{
        return  productProvider.cart.map { it.toDomain() }
    }

    suspend fun addProduct(productItem: ProductItem){
        productProvider.cart.add(productItem.toModel())
        val id = dao.insert(CartEntity(name = productItem.name, img = productItem.img, price = productItem.price))
    }

    suspend fun removeProduct(productItem: ProductItem): Boolean{
        productProvider.cart.remove(productItem.toModel())
        return dao.remove(CartEntity(productItem.id!!,productItem.name, productItem.img, productItem.price)) > 0
    }

    fun removeProduct(index: Int){
        productProvider.cart.removeAt(index)
    }
    suspend fun clean(){
        productProvider.cart.clear()
        dao.remove()
    }

    fun getTotal(): Float {
        return productProvider.cart.map {it.price }.sumOf { fl: Float? -> fl!!.toInt() }.toFloat()
    }

    suspend fun getAllProductsCart(): List<ProductItem> {
        productProvider.cart.clear()
        val response = dao.getAllProducts()
        productProvider.cart.addAll(response.map { it.toDomain().toModel() })
        return productProvider.cart.map { it.toDomain() }
    }
}