package dev.adds.placetopay.ui.common.row

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import dev.adds.placetopay.R
import dev.adds.placetopay.databinding.TransactionItemBinding
import dev.adds.placetopay.model.domain.ShoppingModel
import dev.adds.placetopay.usescase.model.ShoppingItem

class ShoppingRecyclerView(val context: Context, var shoppingItem: MutableList<ShoppingItem>, val update: (shoppingItem: ShoppingItem)-> Unit) :
    RecyclerView.Adapter<ShoppingRecyclerView.ShoppingViewHolder>() {

    class ShoppingViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        private val binding = TransactionItemBinding.bind(itemView)

        fun bin(shoppingItem: ShoppingItem, context: Context, listener: (ShoppingItem) -> Unit) {
            binding.transactionReference.text = "REF: ${shoppingItem.processResponseItem.reference}"
            binding.transactionAmount.text =
                "$${shoppingItem.processResponseItem.amountItem.total} ${shoppingItem.processResponseItem.amountItem.currency}"
            binding.transactionCard.text = context.getString(R.string.shopping_card_number_hint)+shoppingItem.processItem.instrumentItem.cardItem.number.substring(shoppingItem.processItem.instrumentItem.cardItem.number.length-5, shoppingItem.processItem.instrumentItem.cardItem.number.lastIndex)
            binding.transactionStatus.text = shoppingItem.processResponseItem.statusItem.status
            binding.transactionRemove.setOnClickListener {
                listener(shoppingItem)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ShoppingViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.transaction_item, parent, false)
        return ShoppingViewHolder(view)

    }

    override fun onBindViewHolder(holder: ShoppingViewHolder, position: Int) =
        holder.bin(shoppingItem[position], context, update)

    override fun getItemCount(): Int = shoppingItem.size
}