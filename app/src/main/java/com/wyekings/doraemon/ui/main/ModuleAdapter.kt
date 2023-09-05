package com.wyekings.doraemon.ui.main

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import by.kirich1409.viewbindingdelegate.viewBinding
import com.wyekings.doraemon.R
import com.wyekings.doraemon.databinding.ItemModuleBinding
import com.wyekings.doraemon.ui.main.domain.model.Module
import com.wyekings.uikit.recyclerview.adapter.BaseListAdapter
import javax.inject.Inject

/**
 *  @author wye on 9/4/23
 */
class ModuleAdapter @Inject constructor(): BaseListAdapter<Module, ModuleAdapter.ViewHolder>(callback) {

    private lateinit var onClick: (Module) -> Unit

    fun setOnClickListener(onClick: (Module) -> Unit):ModuleAdapter {
        this.onClick = onClick
        return this
    }

    inner class ViewHolder(view:View) : RecyclerView.ViewHolder(view) {

        private val viewBinding:ItemModuleBinding by viewBinding(ItemModuleBinding::bind)

        private lateinit var module: Module

        init {
            viewBinding.root.setOnClickListener {
                onClick.invoke(module)
            }
        }

        fun bind(module: Module) {
            this.module = module
            with(viewBinding) {
                tvModule.text = module.name
            }
        }

    }

    override fun onCreateViewHolder(layoutInflater: LayoutInflater, parent: ViewGroup): ViewHolder {
        val itemView = layoutInflater.inflate(R.layout.item_module,parent,false)
        return ViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: ViewHolder, item: Module) {
        holder.bind(item)
    }

    companion object {
        val callback = object :DiffUtil.ItemCallback<Module>() {
            override fun areItemsTheSame(oldItem: Module, newItem: Module): Boolean {
                return oldItem.name == newItem.name
            }

            override fun areContentsTheSame(oldItem: Module, newItem: Module): Boolean {
                return  oldItem == newItem
            }
        }
    }
}