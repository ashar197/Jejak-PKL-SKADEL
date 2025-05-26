package com.skadel.jejakpklskadel

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import com.skadel.jejakpklskadel.databinding.ActivityMainBinding
import com.skadel.jejakpklskadel.ui.AuthActivity

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        auth = Firebase.auth
        Log.d("Firebase", "Current user: ${auth.currentUser}")
//        if(auth.currentUser == null) {
//            Log.d("Firebase", "User not logged in")
//            startActivity(Intent(this, AuthActivity::class.java))
//            finish()
//            return
//        }
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val navView: BottomNavigationView = binding.navView

        val navController = findNavController(R.id.nav_host_fragment_activity_main)

        val appBarConfiguration = AppBarConfiguration(
            setOf(
                R.id.navigation_home, R.id.navigation_dashboard, R.id.navigation_profile
            )
        )

        setupActionBarWithNavController(navController, appBarConfiguration)
        navView.setupWithNavController(navController)

        checkLogin(navController)
        ViewCompat.setOnApplyWindowInsetsListener(binding.root) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }

    private fun checkLogin(navController: NavController) {
        navController.addOnDestinationChangedListener { controller, destination, arguments ->
            val destinationRequiredLogin = setOf (
                R.id.navigation_dashboard,
                R.id.navigation_profile
            )
            val currentUser = auth.currentUser

            if(destinationRequiredLogin.contains(destination.id)) {
                if(currentUser == null) {
                    controller.popBackStack(destination.id, true)

                    val intent = Intent(this, AuthActivity::class.java)
                    startActivity(intent)

                    Toast.makeText(this, "Silahkan Login terlebih dahulu", Toast.LENGTH_SHORT).show()
                } else {
                    Log.d("AuthActivity", "User ${currentUser.email} already logged in. Acessing ${destination.label}.")
                }
            }
        }
    }
}