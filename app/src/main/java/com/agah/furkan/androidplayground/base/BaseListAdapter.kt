package com.agah.furkan.androidplayground.base

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.ListAdapter
import com.agah.furkan.androidplayground.util.GenericDiffUtil

abstract class BaseListAdapter<VB : ViewDataBinding, T : ListModel> :
    ListAdapter<T, BaseViewHolder<VB>>(GenericDiffUtil()) {

    @get:LayoutRes
    protected abstract val layoutRes: Int

    protected abstract fun bind(binding: VB, item: T)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder<VB> {
        val binding = DataBindingUtil.inflate<VB>(
            LayoutInflater.from(parent.context),
            layoutRes,
            parent,
            false
        )

        return BaseViewHolder(binding)
    }

    override fun onBindViewHolder(holder: BaseViewHolder<VB>, position: Int) {
        bind(holder.binding, getItem(position))
    }
}
