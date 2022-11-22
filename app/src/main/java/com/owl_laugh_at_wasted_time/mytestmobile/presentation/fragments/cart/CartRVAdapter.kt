package com.owl_laugh_at_wasted_time.mytestmobile.presentation.fragments.cart

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.owl_laugh_at_wasted_time.mytestmobile.domain.entity.CartItem
import com.owl_laugh_at_wasted_time.mytestmobile.R
import com.owl_laugh_at_wasted_time.mytestmobile.databinding.ItemCartBinding


class CartRVAdapter : RecyclerView.Adapter<CartRVAdapter.CartItemHolder>() {

    var ontvMinClickListener: ((View) -> Unit)? = null
    var ontvMaxClickListener: ((View) -> Unit)? = null
    var ontvDeleteClickListener: ((View) -> Unit)? = null

    var list: List<CartItem> = emptyList()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CartItemHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemCartBinding.inflate(inflater, parent, false)
        return CartItemHolder(binding, parent.context)
    }

    override fun onBindViewHolder(holder: CartItemHolder, position: Int) {
        val item = list[position]
        holder.bind(item)
    }

    override fun getItemCount(): Int = list.size

    inner class CartItemHolder(
        val binding: ItemCartBinding,
        val context: Context
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(item: CartItem) {
            with(binding) {
                tvMin.tag = item
                tvMax.tag = item
                tvDelete.tag = item
                tvCount.text = item.amount.toString()
                tvPrise.text = item.priseTotal.toString()
                Glide.with(ivPhone)
                    .load(item.image)
                    .diskCacheStrategy(DiskCacheStrategy.NONE)
                    .placeholder(R.drawable.bg_item_placeholder)
                    .error(R.drawable.bottom_sheet_background)
                    .into(ivPhone)
                tvMin.setOnClickListener {
                    ontvMinClickListener?.invoke(it)
                }
                tvMax.setOnClickListener {
                    ontvMaxClickListener?.invoke(it)
                }
                tvDelete.setOnClickListener {
                    ontvDeleteClickListener?.invoke(it)
                }
            }
        }
    }
}
