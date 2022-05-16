package dev.adds.placetopay.ui.order

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.gson.Gson
import dagger.hilt.android.lifecycle.HiltViewModel
import dev.adds.placetopay.model.domain.Card
import dev.adds.placetopay.model.domain.Payer
import dev.adds.placetopay.model.domain.Payment
import dev.adds.placetopay.model.domain.Shopping
import dev.adds.placetopay.model.domain.payment.Process
import dev.adds.placetopay.model.domain.payment.ProcessResponse
import dev.adds.placetopay.usescase.GetProcess
import dev.adds.placetopay.usescase.ManagementOrder
import dev.adds.placetopay.usescase.ManagementPayment
import dev.adds.placetopay.util.Constants
import kotlinx.coroutines.launch
import javax.inject.Inject
@HiltViewModel
class OrderViewModel @Inject constructor(
    private val getProcess: GetProcess,
    private val managementOrder: ManagementOrder,
    private val managementPayment: ManagementPayment
) : ViewModel() {

    private val payer_ = MutableLiveData<Payer>()
    private val payment_ = MutableLiveData<Payment>()
    private val card_ = MutableLiveData<Card>()
    private val order_ = MutableLiveData<Process>()
    private val processResponse_ = MutableLiveData<ProcessResponse>()

    val itsPaying = MutableLiveData<Boolean>()
    val payment : LiveData<Payment> = payment_
    val processResponse : LiveData<ProcessResponse> = processResponse_

    fun setPayer(payer: Payer){
        payer_.value = payer
    }
    fun setCard(card: Card){
        card_.value = card
    }
    fun setPayment(payment: Payment){
        payment_.value = payment
    }

    fun processOrder(){
        managementOrder.newOrder(payer_.value!!, payment_.value!!, card_.value!!)
        viewModelScope.launch {
            itsPaying.postValue(true)
            val result = getProcess(managementOrder.getOrder())
            if(!result.status.equals(Constants.StatusResponse.FAILED.name)){
                processResponse_.postValue(result)

                managementPayment.addPayment(Shopping(
                    managementOrder.getOrder(),result))
            }
            itsPaying.postValue(false)
        }
    }

    fun getOrder() : Process {
        order_.value = managementOrder.getOrder()
        return order_.value!!
    }


}