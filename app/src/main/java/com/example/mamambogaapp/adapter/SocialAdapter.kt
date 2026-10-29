package com.example.mamambogaapp.adapter

import android.content.res.ColorStateList
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.example.mamambogaapp.databinding.ItemSocialBinding
import com.example.mamambogaapp.model.SocialAccount

class SocialAdapter(
    private val socialAccounts: List<SocialAccount>,
    private val onSocialClicked: (SocialAccount) -> Unit
) : RecyclerView.Adapter<SocialAdapter.SocialViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SocialViewHolder {
        val binding = ItemSocialBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return SocialViewHolder(binding)
    }

    override fun onBindViewHolder(holder: SocialViewHolder, position: Int) {
        holder.bind(socialAccounts[position])
    }

    override fun getItemCount(): Int = socialAccounts.size

    inner class SocialViewHolder(
        private val binding: ItemSocialBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(account: SocialAccount) {
            val context = binding.root.context
            binding.imgSocialIcon.setImageResource(account.iconRes)
            binding.tvSocialTitle.text = account.title
            binding.tvSocialHandle.text = account.handleOrNumber
            binding.tvSocialDesc.text = account.description

            val brandColor = ContextCompat.getColor(context, account.colorRes)
            binding.tvSocialHandle.setTextColor(brandColor)
            binding.btnSocialAction.setTextColor(brandColor)
            binding.btnSocialAction.rippleColor = ColorStateList.valueOf(brandColor)

            binding.root.setOnClickListener {
                onSocialClicked(account)
            }

            binding.btnSocialAction.setOnClickListener {
                onSocialClicked(account)
            }
        }
    }
}
