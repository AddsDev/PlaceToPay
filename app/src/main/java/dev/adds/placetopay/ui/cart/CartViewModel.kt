package dev.adds.placetopay.ui.cart

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import dev.adds.placetopay.model.domain.Product
import dev.adds.placetopay.usescase.GetProductsCart
import dev.adds.placetopay.usescase.ManagementCart

class CartViewModel : ViewModel() {

    private val productsList  = MutableLiveData<List<Product>>()
    private val productItem = MutableLiveData<Product>()
    private val totalCart = MutableLiveData<Float>()

    val products : LiveData<List<Product>> = productsList
    val product: LiveData<Product> = productItem
    val total : LiveData<Float> = totalCart

    var getProducts = ManagementCart()

    fun onCreate(){
        productsList.value = getProducts()!!
        computedTotal()
    }
    fun addProduct(product: Product){
        ManagementCart().addProduct(product)
        onCreate()
    }

    fun removeProduct(product: Product): Boolean {
        var flag = ManagementCart().removeProduct(product)
        onCreate()
        return flag
    }
    fun clean() : Unit = ManagementCart().clean()

    private fun computedTotal(){
        totalCart.postValue(ManagementCart().computedTotal())
    }
}