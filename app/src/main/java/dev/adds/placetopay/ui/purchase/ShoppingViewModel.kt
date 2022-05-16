package dev.adds.placetopay.ui.purchase

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import dev.adds.placetopay.model.domain.Product
import dev.adds.placetopay.model.domain.Shopping
import dev.adds.placetopay.usescase.GetProductsCart
import dev.adds.placetopay.usescase.ManagementPayment

class ShoppingViewModel : ViewModel() {
    private val paymentsList = MutableLiveData<List<Shopping>>()

    val payments: LiveData<List<Shopping>> = paymentsList
    var getPayments = ManagementPayment()

    fun onCreate(){
        paymentsList.value = getPayments()
    }

    fun addPayment(shopping: Shopping){
        ManagementPayment().addPayment(shopping)
        onCreate()
    }
    fun removePayment(shopping: Shopping): Boolean{
        var flag = ManagementPayment().removePayment(shopping)
        onCreate()
        return flag
    }

}