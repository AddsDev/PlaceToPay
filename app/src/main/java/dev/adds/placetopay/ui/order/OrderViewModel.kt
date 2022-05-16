package dev.adds.placetopay.ui.order

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import dev.adds.placetopay.model.domain.CardModel
import dev.adds.placetopay.model.domain.PayerModel
import dev.adds.placetopay.model.domain.PaymentModel
import dev.adds.placetopay.model.domain.ShoppingModel
import dev.adds.placetopay.model.domain.payment.ProcessModel
import dev.adds.placetopay.model.domain.payment.ProcessResponseModel
import dev.adds.placetopay.usescase.GetProcess
import dev.adds.placetopay.usescase.ManagementOrder
import dev.adds.placetopay.usescase.ManagementPayment
import dev.adds.placetopay.usescase.model.*
import dev.adds.placetopay.util.Constants
import kotlinx.coroutines.launch
import javax.inject.Inject
@HiltViewModel
class OrderViewModel @Inject constructor(
    private val getProcess: GetProcess,
    private val managementOrder: ManagementOrder,
    private val managementPayment: ManagementPayment
) : ViewModel() {

    private val payer_Item_ = MutableLiveData<PayerItem>()
    private val payment_Item_ = MutableLiveData<PaymentItem>()
    private val card_Item_ = MutableLiveData<CardItem>()
    private val order_ = MutableLiveData<ProcessItem>()
    private val processResponse_Item_ = MutableLiveData<ProcessResponseItem>()

    val itsPaying = MutableLiveData<Boolean>()
    val paymentItem : LiveData<PaymentItem> = payment_Item_
    val processResponseItem : LiveData<ProcessResponseItem> = processResponse_Item_

    fun setPayer(payerItem: PayerItem){
        payer_Item_.value = payerItem
    }
    fun setCard(cardItem: CardItem){
        card_Item_.value = cardItem
    }
    fun setPayment(paymentItem: PaymentItem){
        payment_Item_.value = paymentItem
    }

    fun processOrder(){
        managementOrder.newOrder(payer_Item_.value!!, payment_Item_.value!!, card_Item_.value!!)
        viewModelScope.launch {
            itsPaying.postValue(true)
            val result = getProcess(managementOrder.getOrder())
            if(!result.statusItem.equals(Constants.StatusResponse.FAILED.name)){
                processResponse_Item_.postValue(result)

                managementPayment.addPayment(ShoppingItem(
                    managementOrder.getOrder(),result))
            }
            itsPaying.postValue(false)
        }
    }

    fun getOrder() : ProcessItem {
        order_.value = managementOrder.getOrder()
        return order_.value!!
    }


}