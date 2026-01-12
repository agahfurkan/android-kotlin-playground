package com.agah.furkan.core.view.recyclerview

import androidx.recyclerview.widget.RecyclerView
import androidx.viewbinding.ViewBinding

class BaseViewHolder<T : ViewBinding>(val binding: T) :
    RecyclerView.ViewHolder(binding.root)
