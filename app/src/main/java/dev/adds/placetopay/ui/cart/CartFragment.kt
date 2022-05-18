package dev.adds.placetopay.ui.cart

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.Navigation
import androidx.recyclerview.widget.GridLayoutManager
import dagger.hilt.android.AndroidEntryPoint
import dagger.hilt.android.WithFragmentBindings
import dev.adds.placetopay.R
import dev.adds.placetopay.databinding.FragmentCartBinding
import dev.adds.placetopay.ui.common.row.ProductRecyclerViewAdapter
import dev.adds.placetopay.usescase.model.ProductItem

@WithFragmentBindings
@AndroidEntryPoint
class CartFragment : Fragment() {

    private val cartViewModel: CartViewModel by viewModels()
    private var _binding: FragmentCartBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!


    private var productItems: MutableList<ProductItem> = mutableListOf()

    @SuppressLint("NotifyDataSetChanged")
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentCartBinding.inflate(inflater, container, false)
        val root: View = binding.root




        var productRecyclerViewAdapter = ProductRecyclerViewAdapter(requireContext(), productItems) { item ->
            cartViewModel.removeProduct(item)
        }.apply {
            textButton = R.string.remove
        }

        binding.shopRecyclerProducts.apply {
            layoutManager = GridLayoutManager(requireContext(), 2)
            adapter = productRecyclerViewAdapter
        }
        cartViewModel.products.observe(viewLifecycleOwner){ data ->
            if (data != null) {
                binding.shopItems.text = getString(R.string.items).let { s: String-> s+ data.size }
                binding.shopNext.isEnabled = data.isNotEmpty()
            }
        }
        cartViewModel.total.observe(viewLifecycleOwner) { total ->
            binding.shopTotal.text = getString(R.string.total).let { s: String -> s + total }
        }
        cartViewModel.products.observe(viewLifecycleOwner) { data ->
            productItems.clear()
            productItems.addAll(data!!)
            productRecyclerViewAdapter.notifyDataSetChanged()
        }

        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        cartViewModel.onCreate()
        events()
    }

    private fun events() {
        binding.shopNext.setOnClickListener {
            Navigation.findNavController(it).navigate(R.id.action_navigation_cart_to_orderFragment)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}