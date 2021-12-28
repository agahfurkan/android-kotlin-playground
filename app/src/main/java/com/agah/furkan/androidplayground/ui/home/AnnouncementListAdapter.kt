package com.agah.furkan.androidplayground.ui.home

import com.agah.furkan.androidplayground.R
import com.agah.furkan.androidplayground.base.BaseListAdapter
import com.agah.furkan.androidplayground.databinding.ItemAnnouncementListBinding
import com.agah.furkan.androidplayground.domain.model.result.Announcement

class AnnouncementListAdapter : BaseListAdapter<ItemAnnouncementListBinding, Announcement>() {
    override val layoutRes: Int
        get() = R.layout.item_announcement_list

    override fun bind(binding: ItemAnnouncementListBinding, item: Announcement) {
        binding.item = item
        binding.executePendingBindings()
    }
}

interface AnnouncementListCallback {
    fun onItemClicked(item: Announcement)
}
