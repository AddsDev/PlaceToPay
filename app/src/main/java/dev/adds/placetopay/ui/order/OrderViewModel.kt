package dev.adds.placetopay.ui.order

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import dev.adds.placetopay.model.domain.Card
import dev.adds.placetopay.model.domain.Payer
import dev.adds.placetopay.model.domain.Payment

class OrderViewModel {

    private val payer_ = MutableLiveData<Payer>()
    private val payment_ = MutableLiveData<Payment>()
    private val card_ = MutableLiveData<Card>()

    val payer : LiveData<Payer> = payer_
    val payment : LiveData<Payment> = payment_
    val card: LiveData<Card> = card_

    fun setPayer(payer: Payer){
        payer_.value = payer
    }
    fun setCard(card: Card){
        card_.value = card
    }
    fun setPayment(payment: Payment){
        payment_.value = payment
    }


}