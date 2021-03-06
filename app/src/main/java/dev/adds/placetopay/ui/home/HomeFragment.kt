package dev.adds.placetopay.ui.home

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.GridLayoutManager
import dagger.hilt.android.AndroidEntryPoint
import dagger.hilt.android.WithFragmentBindings
import dev.adds.placetopay.R
import dev.adds.placetopay.databinding.FragmentHomeBinding
import dev.adds.placetopay.ui.cart.CartViewModel
import dev.adds.placetopay.ui.common.row.ProductRecyclerViewAdapter
import dev.adds.placetopay.usescase.model.ProductItem

@WithFragmentBindings
@AndroidEntryPoint
class HomeFragment : Fragment() {
    private val homeViewModel: HomeViewModel by viewModels()
    private val cartViewModel: CartViewModel by viewModels()
    private var _binding: FragmentHomeBinding? = null


    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    private var productItems: MutableList<ProductItem>  = mutableListOf()



    @SuppressLint("NotifyDataSetChanged")
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        val root: View = binding.root


        val productRecyclerViewAdapter = ProductRecyclerViewAdapter(requireContext(), productItems) { item ->
            cartViewModel.addProduct(item)
            Toast.makeText(requireContext(), getString(R.string.home_add_produt) + item.name,
                Toast.LENGTH_SHORT).show()
        }

        binding.homeRecyclerProducts.apply {
            layoutManager = GridLayoutManager(requireContext(), 2)
            adapter = productRecyclerViewAdapter

        }

        homeViewModel.isLoading.observe(viewLifecycleOwner) { status ->
            binding.progressCircular.isVisible = status
        }

        homeViewModel.products.observe(viewLifecycleOwner) { data ->
            productItems.addAll(data)
            productRecyclerViewAdapter.notifyDataSetChanged()
        }

        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        homeViewModel.onCreate()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}