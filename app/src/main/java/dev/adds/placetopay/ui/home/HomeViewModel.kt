package dev.adds.placetopay.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import dev.adds.placetopay.model.domain.ProductModel
import dev.adds.placetopay.usescase.GetProducts
import dev.adds.placetopay.usescase.model.ProductItem
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val getProducts: GetProducts
) : ViewModel() {

    private val productsList = MutableLiveData<List<ProductItem>>()

    val isLoading = MutableLiveData<Boolean>()


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

    val products : LiveData<List<ProductItem>> = productsList

}