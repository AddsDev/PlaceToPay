package dev.adds.placetopay.ui.order

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import dev.adds.placetopay.model.domain.Card
import dev.adds.placetopay.model.domain.Payer
import dev.adds.placetopay.model.domain.Payment
import dev.adds.placetopay.model.domain.payment.Process
import dev.adds.placetopay.usescase.ManagementOrder

class OrderViewModel : ViewModel() {

    private val payer_ = MutableLiveData<Payer>()
    private val payment_ = MutableLiveData<Payment>()
    private val card_ = MutableLiveData<Card>()
    private val order_ = MutableLiveData<Process>()

    val payer : LiveData<Payer> = payer_
    val payment : LiveData<Payment> = payment_
    val card: LiveData<Card> = card_
    val order: LiveData<Process> = order_

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
    }

    fun getOrder() : Process {
        order_.value = ManagementOrder().getOrder()
        return order_.value!!
    }


}