package com.agah.furkan.androidplayground.ui.home

import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.PagerSnapHelper
import androidx.recyclerview.widget.RecyclerView
import com.agah.furkan.androidplayground.R
import com.agah.furkan.androidplayground.databinding.FragmentHomeBinding
import com.agah.furkan.androidplayground.databinding.IncludeAnnouncementListBinding
import com.agah.furkan.androidplayground.domain.model.result.Announcement
import com.agah.furkan.androidplayground.ui.base.BaseFragment
import com.agah.furkan.androidplayground.util.viewBinding

class HomeFragment : BaseFragment(R.layout.fragment_home) {
    private val binding by viewBinding(FragmentHomeBinding::bind)
    private val announcementBinding by viewBinding(IncludeAnnouncementListBinding::bind)
    private var announcementAdapter: AnnouncementListAdapter? = null
    private val listItemWidthPercentage = .75f

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        announcementAdapter = AnnouncementListAdapter()
        announcementBinding.announcementsList.apply {
            adapter = announcementAdapter
            layoutManager = object : LinearLayoutManager(context, HORIZONTAL, false) {
                override fun checkLayoutParams(lp: RecyclerView.LayoutParams): Boolean {
                    lp.width = width.times(listItemWidthPercentage).toInt()
                    return true
                }
            }
        }
        PagerSnapHelper().attachToRecyclerView(announcementBinding.announcementsList)
        announcementAdapter?.submitList(
            listOf(
                Announcement("1"),
                Announcement("2"),
                Announcement("3")
            )
        )
    }

    companion object {
        fun newInstance() = HomeFragment()
    }
}