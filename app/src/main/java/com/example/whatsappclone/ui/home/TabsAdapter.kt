package com.example.whatsappclone.ui.home

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.whatsappclone.ui.EmptyFragment
import com.example.whatsappclone.ui.channel_list.ChannelListFragment
import com.example.whatsappclone.ui.core.EMPTY_TITLE

class TabsAdapter(fragmentManager: FragmentManager, lifecycle: Lifecycle) : FragmentStateAdapter(fragmentManager, lifecycle) {

    override fun getItemCount(): Int = 4

    override fun createFragment(position: Int): Fragment {
        val fragment: Fragment
        when (position) {
            1 -> fragment = ChannelListFragment()
            2 -> {
                fragment = EmptyFragment()
                fragment.arguments = Bundle().apply { putInt(EMPTY_TITLE, position + 1) }
            }
            3 -> {
                fragment = EmptyFragment()
                fragment.arguments = Bundle().apply { putInt(EMPTY_TITLE, position + 1) }
            }
            else -> {
                fragment = EmptyFragment()
            }
        }
        return fragment
    }
}