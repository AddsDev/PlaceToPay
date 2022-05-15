package dev.adds.placetopay.ui.cart

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import dev.adds.placetopay.R
import dev.adds.placetopay.databinding.FragmentCartBinding
import dev.adds.placetopay.model.domain.Product
import dev.adds.placetopay.ui.common.row.ProductRecyclerViewAdapter
import dev.adds.placetopay.ui.order.OrderRouter

class CartFragment : Fragment() {

    private val cartViewModel: CartViewModel by viewModels()
    private var _binding: FragmentCartBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    private var products: MutableList<Product> = mutableListOf()

    @SuppressLint("NotifyDataSetChanged")
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentCartBinding.inflate(inflater, container, false)
        val root: View = binding.root

        var productRecyclerViewAdapter = ProductRecyclerViewAdapter(requireContext(), products) { item ->
            cartViewModel.removeProduct(item)
        }.apply {
            textButton = R.string.remove
        }

        binding.shopRecyclerProducts.apply {
            layoutManager = GridLayoutManager(requireContext(), 2)
            adapter = productRecyclerViewAdapter
        }
        cartViewModel.total.observe(viewLifecycleOwner,{ total->
            binding.shopTotal.text = getString(R.string.total).let { s: String -> s+total }
        })
        cartViewModel.products.observe(viewLifecycleOwner,{ data ->
            //Refactor -> filter
            products.clear()
            products.addAll(data)
            productRecyclerViewAdapter.notifyDataSetChanged()
        })

        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        cartViewModel.onCreate()
        events()
    }

    private fun events() {
        binding.shopNext.setOnClickListener {
            OrderRouter().launch(requireContext())
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}