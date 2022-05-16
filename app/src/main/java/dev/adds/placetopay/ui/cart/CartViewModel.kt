package dev.adds.placetopay.ui.cart

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import dev.adds.placetopay.model.domain.ProductModel
import dev.adds.placetopay.usescase.ManagementCart
import dev.adds.placetopay.usescase.model.ProductItem
import javax.inject.Inject

@HiltViewModel
class CartViewModel @Inject constructor(
    private  val managementCart: ManagementCart
    ) : ViewModel() {

    private val productsList  = MutableLiveData<List<ProductItem>>()
    private val productItem = MutableLiveData<ProductItem>()
    private val totalCart = MutableLiveData<Float>()

    val products : LiveData<List<ProductItem>> = productsList
    val productModel: LiveData<ProductItem> = productItem
    val total : LiveData<Float> = totalCart


    fun onCreate(){
        productsList.value = managementCart()!!
        computedTotal()
    }
    fun addProduct(productItem: ProductItem){
        managementCart.addProduct(productItem)
        onCreate()
    }

    fun removeProduct(productItem: ProductItem): Boolean {
        var flag = managementCart.removeProduct(productItem)
        onCreate()
        return flag
    }
    fun clean() : Unit = managementCart.clean()

    private fun computedTotal(){
        totalCart.postValue(managementCart.computedTotal())
    }
}