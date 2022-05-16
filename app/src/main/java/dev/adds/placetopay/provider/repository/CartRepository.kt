package dev.adds.placetopay.provider.repository

import dev.adds.placetopay.model.domain.Product
import dev.adds.placetopay.provider.ProductProvider
import javax.inject.Inject

class CartRepository @Inject constructor(
    private val productProvider: ProductProvider
){

    fun getAllProducts(): List<Product>{
        return  productProvider.cart
    }

    fun addProduct(product: Product){
        productProvider.cart.add(product)
    }

    fun removeProduct(product: Product): Boolean{
        return productProvider.cart.remove(product)
    }

    fun removeProduct(index: Int){
        productProvider.cart.removeAt(index)
    }
    fun clean(){
        productProvider.cart.clear()
    }

    fun getTotal(): Float {
        return productProvider.cart.map {it.price }.sumOf { fl: Float? -> fl!!.toInt() }.toFloat()
    }
}