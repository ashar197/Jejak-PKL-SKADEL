package com.skadel.jejakpklskadel.ui.profile

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.ViewModelProvider
import com.skadel.jejakpklskadel.MainActivity
import com.skadel.jejakpklskadel.databinding.FragmentProfileBinding
import com.skadel.jejakpklskadel.ui.AuthActivity
import com.skadel.jejakpklskadel.ui.AuthViewModel

class ProfileFragment : Fragment() {

    private var _binding: FragmentProfileBinding? = null
    private val authViewModel: AuthViewModel by activityViewModels()

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val profileViewModel =
            ViewModelProvider(this).get(ProfileViewModel::class.java)

        _binding = FragmentProfileBinding.inflate(inflater, container, false)
        val root: View = binding.root

        authViewModel.logoutComplete.observe(viewLifecycleOwner) { isLogoutComplete ->
            if (isLogoutComplete == true) {
                Log.d("ProfileFragment", "Logout berhasil")
                Toast.makeText(requireContext(), "Logout berhasil", Toast.LENGTH_SHORT).show()
                navigateToHome()
                authViewModel.onLogOutNavigationComplete()
            }
        }

        authViewModel.currentUser.observe(viewLifecycleOwner) { user ->
            if(user != null) {
                binding.textProfile.text = "Masuk sebagai ${user.email}"
            } else {
                binding.textProfile.text = "Anda belum masuk"
            }
        }
        binding.btnLogout.setOnClickListener {
            authViewModel.logoutUser()
        }
        return root
    }

    private fun navigateToHome() {
        Log.d("ProfileFragment", "Navigating to Home")
        val intent = Intent(requireActivity(), MainActivity::class.java)
        intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK
        startActivity(intent)
        requireActivity().finishAffinity()
}
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}