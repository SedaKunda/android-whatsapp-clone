package com.example.whatsappclone.ui.home

import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import androidx.appcompat.app.AppCompatActivity
import androidx.core.graphics.drawable.DrawableCompat
import androidx.fragment.app.Fragment
import com.example.whatsappclone.R
import com.example.whatsappclone.ui.core.TAB_TITLES
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import kotlinx.android.synthetic.main.fragment_home.*

class HomeFragment : Fragment(R.layout.fragment_home) {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.menu_main, menu)
        super.onCreateOptionsMenu(menu,inflater)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        val activity : AppCompatActivity = activity as AppCompatActivity
        activity.setSupportActionBar(toolbar)
        setupViewPager2(initialTab())
        greyCameraIconWhenNotActive(activity)
    }

    private fun setupViewPager2(initialTab: TabLayout.Tab) {
        // connect the tabs and view pager2
        view_pager.adapter = TabsAdapter(childFragmentManager, lifecycle)
        TabLayoutMediator(tabs, view_pager) { tab, position -> tab.text = TAB_TITLES[position]
            if (position == 0) {
                tab.setIcon(R.drawable.ic_camera_alt_black_24dp)
            }
            view_pager.setCurrentItem(tab.position, true)
        }.attach()
        tabs.selectTab(initialTab)
    }

    private fun initialTab(): TabLayout.Tab {
        return tabs.getTabAt(2)!!
    }

    private fun greyCameraIconWhenNotActive(activity: AppCompatActivity) {
        val colors = resources.getColorStateList(R.color.tab_icon, activity.theme)
        for (i in 0 until tabs.tabCount) {
            val tab: TabLayout.Tab = tabs.getTabAt(i)!!
            var icon = tab.icon
            if (icon != null) {
                icon = DrawableCompat.wrap(icon)
                DrawableCompat.setTintList(icon, colors)
            }
        }
    }
}
