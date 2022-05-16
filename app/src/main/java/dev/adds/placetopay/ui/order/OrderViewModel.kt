package dev.adds.placetopay.ui.order

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
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

class OrderViewModel : ViewModel() {

    private val payer_ = MutableLiveData<Payer>()
    private val payment_ = MutableLiveData<Payment>()
    private val card_ = MutableLiveData<Card>()
    private val order_ = MutableLiveData<Process>()
    private val processResponse_ = MutableLiveData<ProcessResponse>()

    val itsPaying = MutableLiveData<Boolean>()
    val payer : LiveData<Payer> = payer_
    val payment : LiveData<Payment> = payment_
    val card: LiveData<Card> = card_
    val order: LiveData<Process> = order_
    val processResponse : LiveData<ProcessResponse> = processResponse_
    var getProcess = GetProcess()

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
        ManagementOrder().newOrder(payer_.value!!, payment_.value!!, card_.value!!)

        viewModelScope.launch {
            itsPaying.postValue(true)
            val result = getProcess(ManagementOrder().getOrder())
            if(!result.status.equals(Constants.StatusResponse.FAILED.name)){
                processResponse_.postValue(result)

                ManagementPayment().addPayment(Shopping(
                    ManagementOrder().getOrder(),result, payer.value!!,card.value!!))
            }
            itsPaying.postValue(false)
        }
    }

    fun getOrder() : Process {
        order_.value = ManagementOrder().getOrder()
        return order_.value!!
    }


}