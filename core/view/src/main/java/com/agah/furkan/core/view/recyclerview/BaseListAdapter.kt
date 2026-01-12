package com.agah.furkan.core.view.recyclerview

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.recyclerview.widget.ListAdapter
import androidx.viewbinding.ViewBinding

abstract class BaseListAdapter<T : BaseListModel, VB : ViewBinding> :
    ListAdapter<T, BaseViewHolder<VB>>(BaseDiffUtil()) {

    @get:LayoutRes
    abstract val layoutId: Int

    abstract fun bind(binding: VB, item: T)
    abstract fun createBinding(inflater: LayoutInflater, parent: ViewGroup): VB
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder<VB> {
        val binding = createBinding(LayoutInflater.from(parent.context), parent)
        return BaseViewHolder(binding)
    }

    override fun onBindViewHolder(holder: BaseViewHolder<VB>, position: Int) {
        bind(holder.binding, getItem(position))
    }
}
