package com.skadel.jejakpklskadel.ui.home

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.ViewModelProvider
import com.skadel.jejakpklskadel.databinding.FragmentHomeBinding
import com.skadel.jejakpklskadel.ui.AuthViewModel

class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null
    private val authViewModel: AuthViewModel by activityViewModels()


    private val binding get() = _binding!!


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val homeViewModel = ViewModelProvider(this).get(HomeViewModel::class.java)

        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        val root: View = binding.root

        authViewModel.currentUser.observe(viewLifecycleOwner) { user ->
            if (user != null) {
                binding.tvWelcomeGreeting.text = "Selamat Datang,"
                binding.tvRekanPkl.text = "${user.email}"
                binding.btnAccessPklId.visibility = View.INVISIBLE
            } else {
                binding.tvWelcomeGreeting.text = "Selamat Datang,"
                binding.tvRekanPkl.text = "Rekan PKL SKADEL"
                binding.btnAccessPklId.visibility = View.VISIBLE
            }
        }

        return root
    }
     override fun onDestroyView() {
         super.onDestroyView()
         _binding = null
    }
}