package com.skadel.jejakpklskadel

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
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import com.skadel.jejakpklskadel.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

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

//
//        setContentView(R.layout.activity_main)
//
//        auth = Firebase.auth
//
//        val emailEditText: EditText = findViewById(R.id.emailEditText)
//        val passwordEditText: EditText = findViewById(R.id.passwordEditText)
//        val registerButton: Button = findViewById(R.id.registerButton)
//        val loginButton: Button = findViewById(R.id.loginButton)
//
//        registerButton.setOnClickListener {
//            val email = emailEditText.text.toString()
//            val password = passwordEditText.text.toString()
//
//            if(email.isNotEmpty() && password.isNotEmpty()) {
//                auth.createUserWithEmailAndPassword(email, password)
//                    .addOnCompleteListener(this) { task ->
//                        if (task.isSuccessful) {
//                            // Pendaftaran berhasil
//                            Log.d("Firebase", "createUserWithEmail:success")
//                            val user = auth.currentUser
//                            Toast.makeText(baseContext, "Pendaftaran Berhasil untuk ${user?.email}", Toast.LENGTH_SHORT).show()
//                        } else {
//                            // Pendaftaran gagal
//                            Log.w("Firebase", "createUserWithEmail:failure", task.exception)
//                            Toast.makeText(baseContext, "Pendaftaran Gagal.", Toast.LENGTH_SHORT).show()
//                        }
//
//                    }
//            } else {
//                Toast.makeText(this, "Email dan Password harus diisi", Toast.LENGTH_SHORT).show()
//
//            }
//        }
//        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
//            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
//            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
//            insets
//        }
    }
}