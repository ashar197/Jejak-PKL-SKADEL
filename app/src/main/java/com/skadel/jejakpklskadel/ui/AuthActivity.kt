package com.skadel.jejakpklskadel.ui

import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import com.skadel.jejakpklskadel.databinding.ActivityAuthBinding

class AuthActivity : AppCompatActivity() {
    private lateinit var auth: FirebaseAuth
    private lateinit var binding: ActivityAuthBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        binding = ActivityAuthBinding.inflate(layoutInflater)
        setContentView(binding.root)
        auth = Firebase.auth

        val emailEditText = binding.emailEditText
        val passwordEditText = binding.passwordEditText
        val registerButton = binding.registerButton
        val loginButton = binding.loginButton

        registerButton.setOnClickListener {
            val email = emailEditText.text.toString()
            val password = passwordEditText.text.toString()

            if(email.isNotEmpty() && password.isNotEmpty()) {
                auth.createUserWithEmailAndPassword(email, password)
                    .addOnCompleteListener(this) { task ->
                        if (task.isSuccessful) {
                            // Pendaftaran berhasil
                            Log.d("Firebase", "createUserWithEmail:success")
                            val user = auth.currentUser
                            Toast.makeText(baseContext, "Pendaftaran Berhasil untuk ${user?.email}", Toast.LENGTH_SHORT).show()
                        } else {
                            // Pendaftaran gagal
                            Log.w("Firebase", "createUserWithEmail:failure", task.exception)
                            Toast.makeText(baseContext, "Pendaftaran Gagal.", Toast.LENGTH_SHORT).show()
                        }

                    }
            } else {
                Toast.makeText(this, "Email dan Password harus diisi", Toast.LENGTH_SHORT).show()

            }
        }


//        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
//            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
//            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
//            insets
//        }
    }
}