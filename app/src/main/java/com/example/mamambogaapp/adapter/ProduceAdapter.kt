package com.example.mamambogaapp.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.mamambogaapp.databinding.ItemProduceBinding
import com.example.mamambogaapp.model.ProduceItem

class ProduceAdapter(
    private val onAddToCartClicked: (ProduceItem) -> Unit
) : ListAdapter<ProduceItem, ProduceAdapter.ProduceViewHolder>(ProduceDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProduceViewHolder {
        val binding = ItemProduceBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return ProduceViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ProduceViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    inner class ProduceViewHolder(
        private val binding: ItemProduceBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(item: ProduceItem) {
            binding.imgProduceIcon.setImageResource(item.iconResId)
            binding.tvProduceName.text = item.name
            binding.tvSwahiliName.text = "${item.swahiliName} • ${item.unit}"
            binding.tvPriceKsh.text = "KSh ${item.priceKsh.toInt()}"

            if (item.isPopular) {
                binding.tvPopularTag.visibility = View.VISIBLE
            } else {
                binding.tvPopularTag.visibility = View.GONE
            }

            binding.btnAddToCart.setOnClickListener {
                onAddToCartClicked(item)
            }
        }
    }

    private class ProduceDiffCallback : DiffUtil.ItemCallback<ProduceItem>() {
        override fun areItemsTheSame(oldItem: ProduceItem, newItem: ProduceItem): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: ProduceItem, newItem: ProduceItem): Boolean {
            return oldItem == newItem
        }
    }
}
