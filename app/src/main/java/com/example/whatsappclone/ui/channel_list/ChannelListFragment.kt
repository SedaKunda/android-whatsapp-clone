package com.example.whatsappclone.ui.channel_list

/* https://getstream.io/tutorials/android-chat/#kotlin */

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.whatsappclone.databinding.FragmentChannelListBinding
import com.example.whatsappclone.ui.home.HomeFragmentDirections
import com.getstream.sdk.chat.StreamChat
import com.getstream.sdk.chat.enums.Filters.*
import com.getstream.sdk.chat.rest.User
import com.getstream.sdk.chat.viewmodel.ChannelListViewModel

const val API_KEY = "s2dxdhpxd94g"
const val USER_ID = "empty-queen-5"
const val USER_TOKEN = "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJ1c2VyX2lkIjoiZW1wdHktcXVlZW4tNSJ9.RJw-XeaPnUBKbbh71rV1bYAKXp6YaPARh68O08oRnOU" //todo generate token at login/sign-up

class ChannelListFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val binding = FragmentChannelListBinding.inflate(inflater, container, false)
        connectToStream()
        setupBinding(binding)
//        val adapter =  ChannelListItemAdapter(activity)
//        adapter.setViewHolderFactory(CustomViewHolderFactory())
        binding.channelList.setOnChannelClickListener { channel -> findNavController().navigate(HomeFragmentDirections.navHomeToChannel(channel.type, channel.id)) }
        return binding.root
    }

    private fun setupBinding(binding: FragmentChannelListBinding) {
        val viewModel: ChannelListViewModel by viewModels()
        binding.lifecycleOwner = this
        binding.viewModel = viewModel
        binding.channelList.setViewModel(viewModel, this)
        val filter = and(eq("type", "messaging"), `in`("members", "empty-queen-5"))
        viewModel.setChannelFilter(filter)
    }

    private fun connectToStream() {
        val configuration = StreamChat.Config(activity!!.applicationContext, API_KEY)
        StreamChat.init(configuration) //setup client using API key

        val client = StreamChat.getInstance(activity!!.application)
        val extraData = HashMap<String, Any>()
        extraData["name"] = "Paranoid Android"
        extraData["image"] = "https://bit.ly/2TIt8NR"
        val currentUser = User(USER_ID, extraData)

        client.setUser(currentUser, USER_TOKEN) //authenticate current user
    }
}


