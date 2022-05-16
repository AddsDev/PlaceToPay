package dev.adds.placetopay.provider.repository

import dev.adds.placetopay.model.domain.ProductModel
import dev.adds.placetopay.provider.ProductProvider
import javax.inject.Inject

class CartRepository @Inject constructor(
    private val productProvider: ProductProvider
){

    fun getAllProducts(): List<ProductModel>{
        return  productProvider.cart
    }

    fun addProduct(productModel: ProductModel){
        productProvider.cart.add(productModel)
    }

    fun removeProduct(productModel: ProductModel): Boolean{
        return productProvider.cart.remove(productModel)
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