package com.example.mamambogaapp.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.mamambogaapp.databinding.ItemCartBinding
import com.example.mamambogaapp.model.CartItem

class CartAdapter(
    private val onQuantityPlus: (CartItem) -> Unit,
    private val onQuantityMinus: (CartItem) -> Unit,
    private val onItemRemove: (CartItem) -> Unit
) : ListAdapter<CartItem, CartAdapter.CartViewHolder>(CartDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CartViewHolder {
        val binding = ItemCartBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return CartViewHolder(binding)
    }

    override fun onBindViewHolder(holder: CartViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    inner class CartViewHolder(
        private val binding: ItemCartBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(cartItem: CartItem) {
            val item = cartItem.item
            binding.imgCartItemIcon.setImageResource(item.iconResId)
            binding.tvCartItemName.text = item.name
            binding.tvCartItemPriceUnit.text = "KSh ${item.priceKsh.toInt()} / ${item.unit}"
            binding.tvCartItemQuantity.text = cartItem.quantity.toString()
            binding.tvCartItemSubtotal.text = "Total: KSh ${cartItem.totalKsh.toInt()}"

            binding.btnCartPlus.setOnClickListener {
                onQuantityPlus(cartItem)
            }

            binding.btnCartMinus.setOnClickListener {
                onQuantityMinus(cartItem)
            }

            binding.btnRemoveCartItem.setOnClickListener {
                onItemRemove(cartItem)
            }
        }
    }

    private class CartDiffCallback : DiffUtil.ItemCallback<CartItem>() {
        override fun areItemsTheSame(oldItem: CartItem, newItem: CartItem): Boolean {
            return oldItem.item.id == newItem.item.id
        }

        override fun areContentsTheSame(oldItem: CartItem, newItem: CartItem): Boolean {
            return oldItem.quantity == newItem.quantity && oldItem.item == newItem.item
        }
    }
}
