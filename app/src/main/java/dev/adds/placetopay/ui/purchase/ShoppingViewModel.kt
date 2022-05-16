package dev.adds.placetopay.ui.purchase

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import dev.adds.placetopay.model.domain.Product
import dev.adds.placetopay.model.domain.Shopping
import dev.adds.placetopay.usescase.GetProductsCart
import dev.adds.placetopay.usescase.ManagementPayment
import javax.inject.Inject

@HiltViewModel
class ShoppingViewModel @Inject constructor(
    private val managementPayment: ManagementPayment
) : ViewModel() {
    private val paymentsList = MutableLiveData<List<Shopping>>()

    val payments: LiveData<List<Shopping>> = paymentsList

    fun onCreate(){
        paymentsList.value = managementPayment()
    }

    fun addPayment(shopping: Shopping){
        managementPayment.addPayment(shopping)
        onCreate()
    }
    fun removePayment(shopping: Shopping): Boolean{
        var flag = managementPayment.removePayment(shopping)
        onCreate()
        return flag
    }

}