package com.skadel.jejakpklskadel.ui.splash

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.animation.AnimationUtils
import androidx.appcompat.app.AppCompatActivity
import com.skadel.jejakpklskadel.MainActivity
import com.skadel.jejakpklskadel.R
import com.skadel.jejakpklskadel.databinding.ActivitySplashBinding

class SplashActivity : AppCompatActivity() {
    private val SPLASH_TIME_OUT: Long = 3000 //3 detik
    private lateinit var binding: ActivitySplashBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySplashBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val logoImageView = binding.ivLogo
        val rootView = binding.splashRootLayout

        val scaleAnimation = AnimationUtils.loadAnimation(this, R.anim.scale_animation)
        logoImageView.startAnimation(scaleAnimation)

        Handler(Looper.getMainLooper()).postDelayed({
            val fadeOutAnimation = AnimationUtils.loadAnimation(this, R.anim.fade_out)
            fadeOutAnimation.setAnimationListener(object : android.view.animation.Animation.AnimationListener {
                override fun onAnimationStart(animation: android.view.animation.Animation?) {}
                override fun onAnimationEnd(animation: android.view.animation.Animation?) {
                    val i = Intent(this@SplashActivity, MainActivity::class.java)
                    startActivity(i)
                    finish()
                }
                override fun onAnimationRepeat(animation: android.view.animation.Animation?) {}
            })
            rootView.startAnimation(fadeOutAnimation)
        }, SPLASH_TIME_OUT)
    }
}