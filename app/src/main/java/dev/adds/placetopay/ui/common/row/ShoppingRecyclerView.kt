package dev.adds.placetopay.ui.common.row

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import dev.adds.placetopay.R
import dev.adds.placetopay.databinding.TransactionItemBinding
import dev.adds.placetopay.model.domain.Shopping
import dev.adds.placetopay.model.domain.payment.ProcessResponse

class ShoppingRecyclerView(val context: Context, var shopping: MutableList<Shopping>, val update: (shopping: Shopping)-> Unit) :
    RecyclerView.Adapter<ShoppingRecyclerView.ShoppingViewHolder>() {

    class ShoppingViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        private val binding = TransactionItemBinding.bind(itemView)

        fun bin(shopping: Shopping, context: Context,listener: (Shopping) -> Unit) {
            binding.transactionReference.text = "REF: ${shopping.processResponse.reference}"
            binding.transactionAmount.text =
                "$${shopping.processResponse.amount.total} ${shopping.processResponse.amount.currency}"
            binding.transactionCard.text = context.getString(R.string.shopping_card_number_hint)+shopping.process.instrument.card.number.substring(shopping.process.instrument.card.number.length-5, shopping.process.instrument.card.number.lastIndex)
            binding.transactionStatus.text = shopping.processResponse.status.status
            binding.transactionRemove.setOnClickListener {
                listener(shopping)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ShoppingViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.transaction_item, parent, false)
        return ShoppingViewHolder(view)

    }

    override fun onBindViewHolder(holder: ShoppingViewHolder, position: Int) =
        holder.bin(shopping[position], context, update)

    override fun getItemCount(): Int = shopping.size
}