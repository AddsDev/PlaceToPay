package dev.adds.placetopay.ui.order

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.Navigation
import com.google.gson.Gson
import dagger.hilt.android.AndroidEntryPoint
import dagger.hilt.android.WithFragmentBindings
import dev.adds.placetopay.R
import dev.adds.placetopay.databinding.FragmentOrderBinding
import dev.adds.placetopay.ui.cart.CartViewModel
import dev.adds.placetopay.usescase.model.AmountItem
import dev.adds.placetopay.usescase.model.CardItem
import dev.adds.placetopay.usescase.model.PayerItem
import dev.adds.placetopay.usescase.model.PaymentItem
import dev.adds.placetopay.util.Constants
import dev.adds.placetopay.util.extension.apiFormat
import java.util.*

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

            val cardItem = CardItem(binding.orderCardNumber.text.toString(),
                "${binding.orderCardMonth.text.toString()}/${binding.orderCardYear.text.toString()}",
                binding.orderCardCVV.text.toString(), 0 )

            val payerItem = PayerItem(binding.orderName.text.toString(),
                binding.orderSurname.text.toString(),
                binding.orderEmail.text.toString(),
                "CC" ,
                binding.orderDocument.text.toString(),
                binding.orderMobile.text.toString())


            val paymentItem = PaymentItem(binding.orderDocument.text.toString(),
                Date().apiFormat(),
                AmountItem(Constants.Currency.USD.currency, cartViewModel.total.value!!.toInt())
            )


            orderViewModel.setCard(cardItem)
            orderViewModel.setPayer(payerItem)
            orderViewModel.setPayment(paymentItem)
            startTransaction()
        }

    }

    private fun startTransaction() {
        orderViewModel.processOrder()
        cartViewModel.clean()
        binding.nestedScrollView.isEnabled = false
        binding.orderNext.text = getString(R.string.card_paying)
        orderViewModel.itsPaying.observe(viewLifecycleOwner) { flag ->
            if (!flag)
                endTransaction()
        }
        orderViewModel.processResponseItem.observe(viewLifecycleOwner) {
            Log.i("RESPONSE_API", Gson().toJson(it))
        }

    }

    private fun endTransaction() {
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