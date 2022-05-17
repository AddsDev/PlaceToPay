package dev.adds.placetopay.ui.cart

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import dev.adds.placetopay.usescase.ManagementCart
import dev.adds.placetopay.usescase.model.ProductItem
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CartViewModel @Inject constructor(
    private  val managementCart: ManagementCart
    ) : ViewModel() {

    private val productsList  = MutableLiveData<List<ProductItem>?>()
    private val productItem = MutableLiveData<ProductItem>()
    private val totalCart = MutableLiveData<Float>()

    val products : MutableLiveData<List<ProductItem>?> = productsList
    val productModel: LiveData<ProductItem> = productItem
    val total : LiveData<Float> = totalCart


    fun onCreate(){
        viewModelScope.launch {
            productsList.value = managementCart()
            computedTotal()
        }
    }
    private fun getCart(){
        viewModelScope.launch {
            productsList.value = managementCart.getCart()
            computedTotal()
        }
    }
    fun addProduct(productItem: ProductItem){
        viewModelScope.launch {
            managementCart.addProduct(productItem)
            getCart()
        }
    }

    fun removeProduct(productItem: ProductItem): Unit {
        viewModelScope.launch {
            managementCart.removeProduct(productItem)
            getCart()
        }
    }
    fun clean() : Unit {
        viewModelScope.launch {
            managementCart.clean()
            getCart()
        }
    }

    private fun computedTotal(){
        totalCart.postValue(managementCart.computedTotal())
    }
}