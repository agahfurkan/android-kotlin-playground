package com.agah.furkan.androidplayground.ui.adapter.recyclerview

import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import androidx.viewbinding.ViewBinding
import com.agah.furkan.androidplayground.base.ListModel
import com.agah.furkan.androidplayground.util.GenericDiffUtil

abstract class GenericListAdapter<T : ListModel>(private val viewHolderLayout: Class<*>) :
    ListAdapter<T, GenericListAdapter.GenericViewHolder<T>>(GenericDiffUtil()) {
    var mListAdapterListener: GenericListAdapterListener<T>? = null

    override fun onBindViewHolder(holder: GenericViewHolder<T>, position: Int) {
        holder.bind(getItem(position))
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GenericViewHolder<T> {
        return ViewHolderFactory.create<T>(parent, viewHolderLayout, this).apply {
            listAdapterListener = mListAdapterListener
        }
    }

    abstract class GenericViewHolder<T : ListModel>(binding: ViewBinding) :
        RecyclerView.ViewHolder(binding.root) {
        var listAdapterListener: GenericListAdapterListener<T>? = null
        lateinit var adapter: GenericListAdapter<T>
        abstract fun bind(item: T)
    }

    interface GenericListAdapterListener<T : ListModel> {
        fun onItemRemoveClicked(adapter: GenericListAdapter<T>, item: T) {}
        fun onItemClicked(adapter: GenericListAdapter<T>, item: T) {}
    }
}
