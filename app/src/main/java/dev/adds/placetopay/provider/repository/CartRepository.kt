package dev.adds.placetopay.provider.repository

import dev.adds.placetopay.model.domain.Product
import dev.adds.placetopay.provider.ProductProvider

class CartRepository {

    fun getAllProducts(): List<Product>{
        return  ProductProvider.cart
    }

    fun addProduct(product: Product){
        ProductProvider.cart.add(product)
    }

    fun removeProduct(product: Product): Boolean{
        return ProductProvider.cart.remove(product)
    }

    fun removeProduct(index: Int){
        ProductProvider.cart.removeAt(index)
    }

    fun getTotal(): Float {
        return ProductProvider.cart.map {it.price }.sumOf { fl: Float? -> fl!!.toInt() }.toFloat()
    }
}