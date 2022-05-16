package dev.adds.placetopay.ui.cart

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import dev.adds.placetopay.model.domain.Product
import dev.adds.placetopay.usescase.ManagementCart
import javax.inject.Inject

@HiltViewModel
class CartViewModel @Inject constructor(
    private  val managementCart: ManagementCart
    ) : ViewModel() {

    private val productsList  = MutableLiveData<List<Product>>()
    private val productItem = MutableLiveData<Product>()
    private val totalCart = MutableLiveData<Float>()

    val products : LiveData<List<Product>> = productsList
    val product: LiveData<Product> = productItem
    val total : LiveData<Float> = totalCart


    fun onCreate(){
        productsList.value = managementCart()!!
        computedTotal()
    }
    fun addProduct(product: Product){
        managementCart.addProduct(product)
        onCreate()
    }

    fun removeProduct(product: Product): Boolean {
        var flag = managementCart.removeProduct(product)
        onCreate()
        return flag
    }
    fun clean() : Unit = managementCart.clean()

    private fun computedTotal(){
        totalCart.postValue(managementCart.computedTotal())
    }
}