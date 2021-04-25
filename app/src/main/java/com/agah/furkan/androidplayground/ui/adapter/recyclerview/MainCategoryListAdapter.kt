package com.agah.furkan.androidplayground.ui.adapter.recyclerview

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.agah.furkan.androidplayground.data.web.model.response.CategoryResponse
import com.agah.furkan.androidplayground.databinding.ItemMainCategoryListBinding
import com.agah.furkan.androidplayground.util.GenericDiffUtil

class MainCategoryListAdapter(private val categoryListAdapterListener: CategoryListAdapterListener) :
    ListAdapter<CategoryResponse.Category, MainCategoryListAdapter.CategoryViewHolder>(
        GenericDiffUtil()
    ) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoryViewHolder {
        return CategoryViewHolder.from(parent)
    }

    override fun onBindViewHolder(holder: CategoryViewHolder, position: Int) {
        holder.bind(getItem(position), categoryListAdapterListener)
    }

    class CategoryViewHolder(private val binding: ItemMainCategoryListBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(
            item: CategoryResponse.Category,
            categoryListListener: CategoryListAdapterListener
        ) {
            binding.itemMainCategoryName.text = item.categoryName
            binding.root.setOnClickListener {
                categoryListListener.onItemClicked(item)
            }
        }

        companion object {
            fun from(parent: ViewGroup): CategoryViewHolder {
                val binding = ItemMainCategoryListBinding.inflate(
                    LayoutInflater.from(parent.context),
                    parent,
                    false
                )
                return CategoryViewHolder(binding)
            }
        }
    }

    interface CategoryListAdapterListener {
        fun onItemClicked(item: CategoryResponse.Category)
    }
}