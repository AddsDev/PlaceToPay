package dev.adds.placetopay.ui.common.row

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import dev.adds.placetopay.ui.common.row.ProductRecyclerViewAdapter.ProductViewHolder
import android.view.ViewGroup
import com.squareup.picasso.Picasso
import dev.adds.placetopay.R
import dev.adds.placetopay.databinding.ProductItemBinding
import dev.adds.placetopay.usescase.model.ProductItem

class ProductRecyclerViewAdapter(val context: Context, var productItem: MutableList<ProductItem>, val update: (productItem: ProductItem)-> Unit) : RecyclerView.Adapter<ProductViewHolder>() {

    var textButton: Int = R.string.add

    fun setNewText(textResource: Int){
        textButton = textResource
    }
    class ProductViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        private val binding  = ProductItemBinding.bind(itemView)

        fun bin(productItem: ProductItem, listener: (ProductItem) -> Unit){
            binding.priceProduct.text = "$ ${productItem.price}"
            binding.nameProduct.text = productItem.name
            Picasso.get().load("https://via.placeholder.com/250/FFFFFF/000000/?text=${productItem.id}")
                .placeholder(R.drawable.ic_shopping_bag_black_24dp)
                .error(R.drawable.ic_shopping_cart_black_24dp)
                .into(binding.imageProduct)
            binding.addProduct.setOnClickListener {
                listener(productItem)
            }
        }

    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.product_item, parent, false)
        val viewHolder = ProductViewHolder(view)
        //UI
        val binding = ProductItemBinding.bind(view)
        binding.addProduct.text = context.getString(textButton)
        return viewHolder
    }

    override fun onBindViewHolder(holder: ProductViewHolder, position: Int) = holder.bin(productItem[position], update)

    override fun getItemCount(): Int = productItem.size


}