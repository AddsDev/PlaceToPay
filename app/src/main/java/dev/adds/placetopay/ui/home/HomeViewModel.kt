package dev.adds.placetopay.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dev.adds.placetopay.model.domain.Product
import dev.adds.placetopay.usescase.GetProducts
import kotlinx.coroutines.launch

class HomeViewModel : ViewModel() {

    private val productsList = MutableLiveData<List<Product>>()

    val isLoading = MutableLiveData<Boolean>()

    var getProducts = GetProducts()

    fun onCreate(){
        viewModelScope.launch {
            isLoading.postValue(true)
            val result  = getProducts()
            if(!result.isNullOrEmpty()) {
                productsList.postValue(result!!)
                isLoading.postValue(false)
            }
        }
    }

    val products : LiveData<List<Product>> = productsList

}