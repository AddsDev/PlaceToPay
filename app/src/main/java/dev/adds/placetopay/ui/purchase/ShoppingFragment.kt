package dev.adds.placetopay.ui.purchase

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.GridLayout
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import dagger.hilt.android.AndroidEntryPoint
import dagger.hilt.android.WithFragmentBindings
import dev.adds.placetopay.databinding.FragmentShoppingBinding
import dev.adds.placetopay.model.domain.Payment
import dev.adds.placetopay.model.domain.Product
import dev.adds.placetopay.model.domain.Shopping
import dev.adds.placetopay.ui.common.row.ProductRecyclerViewAdapter
import dev.adds.placetopay.ui.common.row.ShoppingRecyclerView

@WithFragmentBindings
@AndroidEntryPoint
class ShoppingFragment : Fragment() {

    private lateinit var shoppingViewModel: ShoppingViewModel
    private var _binding: FragmentShoppingBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    private var payments: MutableList<Shopping> = mutableListOf()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        shoppingViewModel =
            ViewModelProvider(this).get(ShoppingViewModel::class.java)

        _binding = FragmentShoppingBinding.inflate(inflater, container, false)

        val root: View = binding.root

        val paymentRecyclerViewAdapter = ShoppingRecyclerView(requireContext(), payments) { item ->
            shoppingViewModel.removePayment(item)
        }
        
        val shoppingRecyclerView = binding.shoppingRecyclerPayments.apply {
            layoutManager = LinearLayoutManager(requireContext())
            adapter = paymentRecyclerViewAdapter
        }

        shoppingViewModel.payments.observe(viewLifecycleOwner,{ data->
            payments.clear()
            payments.addAll(data)
            paymentRecyclerViewAdapter.notifyDataSetChanged()
        })

        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        shoppingViewModel.onCreate()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}