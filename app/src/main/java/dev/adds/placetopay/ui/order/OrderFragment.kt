package dev.adds.placetopay.ui.order

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.navigation.Navigation
import com.google.gson.Gson
import dagger.hilt.android.AndroidEntryPoint
import dagger.hilt.android.WithFragmentBindings
import dev.adds.placetopay.R
import dev.adds.placetopay.databinding.FragmentOrderBinding
import dev.adds.placetopay.model.domain.Amount
import dev.adds.placetopay.model.domain.Card
import dev.adds.placetopay.model.domain.Payer
import dev.adds.placetopay.model.domain.Payment
import dev.adds.placetopay.ui.cart.CartViewModel
import dev.adds.placetopay.util.Constants
import dev.adds.placetopay.util.extension.apiFormat
import java.util.*
import kotlin.concurrent.schedule

@WithFragmentBindings
@AndroidEntryPoint
class OrderFragment : Fragment() {

    private val orderViewModel : OrderViewModel by viewModels()
    private val cartViewModel: CartViewModel by viewModels()

    private var _binding: FragmentOrderBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentOrderBinding.inflate(inflater, container, false)
        setHasOptionsMenu(true)
        val root = binding.root

        cartViewModel.onCreate()
        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        events()
    }

    private fun events() {
        binding.orderNext.setOnClickListener {

            val card = Card(binding.orderCardNumber.text.toString(),
                "${binding.orderCardMonth.text.toString()}/${binding.orderCardYear.text.toString()}",
                binding.orderCardCVV.text.toString(), 0 )

            val payer = Payer(binding.orderName.text.toString(),
                binding.orderSurname.text.toString(),
                binding.orderEmail.text.toString(),
                "CC" ,
                binding.orderDocument.text.toString(),
                binding.orderMobile.text.toString())


            val payment = Payment(binding.orderDocument.text.toString(),
                Date().apiFormat(),
                Amount(Constants.Currency.Dollar.currency, cartViewModel.total.value!!.toInt())
            )


            orderViewModel.setCard(card)
            orderViewModel.setPayer(payer)
            orderViewModel.setPayment(payment)
            startTransaction()
        }

    }

    private fun startTransaction() {
        //Pay
        orderViewModel.processOrder()
        cartViewModel.clean()
        binding.nestedScrollView.isEnabled = false
        binding.orderNext.text = getString(R.string.card_paying)
        orderViewModel.itsPaying.observe(viewLifecycleOwner,{ flag ->
            if(!flag)
                endTransanction()
        })
        orderViewModel.processResponse.observe(viewLifecycleOwner,{
            Log.i("RESPONSE_API", Gson().toJson(it))
        })

    }

    private fun endTransanction() {
        binding.orderNext.text =  getString(R.string.card_paying_finalized)
        Navigation.findNavController(binding.root).apply {
            popBackStack(R.id.navigation_cart, true)
            navigate(R.id.navigation_shopping)
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if(item.itemId == android.R.id.home){
            requireActivity().onBackPressed()
            return true
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}