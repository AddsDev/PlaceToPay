package dev.adds.placetopay.ui.purchase

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import dev.adds.placetopay.usescase.ManagementPayment
import dev.adds.placetopay.usescase.model.ShoppingItem
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ShoppingViewModel @Inject constructor(
    private val managementPayment: ManagementPayment
) : ViewModel() {
    private val paymentsList = MutableLiveData<List<ShoppingItem>?>()

    val payments: MutableLiveData<List<ShoppingItem>?> = paymentsList

    fun onCreate(){
        viewModelScope.launch {
            paymentsList.value = managementPayment()
        }
    }

    suspend fun addPayment(shoppingItem: ShoppingItem){
        managementPayment.addItem(shoppingItem)
        onCreate()
    }
    fun removePayment(shoppingItem: ShoppingItem): Unit {
        viewModelScope.launch {
            managementPayment.removeItem(shoppingItem)
            onCreate()
        }
    }

}