package com.skadel.jejakpklskadel.ui.home

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.skadel.jejakpklskadel.databinding.FragmentHomeBinding

class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null

    private val binding get() = _binding!!


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val homeViewModel = ViewModelProvider(this).get(HomeViewModel::class.java)

        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        val root: View = binding.root

        val textGreetingWelcome: TextView = binding.tvWelcomeGreeting
        val textUsername: TextView = binding.tvRekanPkl

        homeViewModel.welcomeGreeting.observe(viewLifecycleOwner) {
            textGreetingWelcome.text = it
        }

        homeViewModel.userName.observe(viewLifecycleOwner) {
            textUsername.text = it
        }


        return root
    }
     override fun onDestroyView() {
         super.onDestroyView()
         _binding = null
    }
}