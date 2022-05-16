package dev.adds.placetopay.ui.purchase

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import dev.adds.placetopay.model.domain.ShoppingModel
import dev.adds.placetopay.usescase.ManagementPayment
import dev.adds.placetopay.usescase.model.ShoppingItem
import javax.inject.Inject

@HiltViewModel
class ShoppingViewModel @Inject constructor(
    private val managementPayment: ManagementPayment
) : ViewModel() {
    private val paymentsList = MutableLiveData<List<ShoppingItem>>()

    val payments: LiveData<List<ShoppingItem>> = paymentsList

    fun onCreate(){
        paymentsList.value = managementPayment()
    }

    fun addPayment(shoppingItem: ShoppingItem){
        managementPayment.addPayment(shoppingItem)
        onCreate()
    }
    fun removePayment(shoppingItem: ShoppingItem): Boolean{
        var flag = managementPayment.removePayment(shoppingItem)
        onCreate()
        return flag
    }

}