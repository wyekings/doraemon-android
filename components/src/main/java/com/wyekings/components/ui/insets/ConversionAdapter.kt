package com.wyekings.components.ui.insets

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import by.kirich1409.viewbindingdelegate.viewBinding
import com.wyekings.components.R
import com.wyekings.components.databinding.ItemMessageBubbleSelfBinding
import com.wyekings.uikit.recyclerview.adapter.BaseListAdapter

class ConversionAdapter : BaseListAdapter<Conversion, ConversionAdapter.ViewHolder>(DIFF_CALLBACK) {

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(conversion: Conversion) {
            with(viewBinding.tvMessage) {
                text = conversion.message
            }
        }

        private val viewBinding by viewBinding(ItemMessageBubbleSelfBinding::bind)

    }

    override fun onCreateViewHolder(layoutInflater: LayoutInflater, parent: ViewGroup): ViewHolder {
        val itemView = layoutInflater.inflate(R.layout.item_message_bubble_self, parent, false)
        return ViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: ViewHolder, item: Conversion) {
        holder.bind(item)
    }

    companion object {
        val DIFF_CALLBACK = object : DiffUtil.ItemCallback<Conversion>() {
            override fun areItemsTheSame(oldItem: Conversion, newItem: Conversion): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(oldItem: Conversion, newItem: Conversion): Boolean {
                return oldItem == newItem
            }
        }
    }
}