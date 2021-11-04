package com.example.addcardapp

import android.os.Bundle
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import androidx.appcompat.app.AppCompatActivity
import com.example.addcardapp.databinding.ActivitySuccessBinding

class SuccessActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySuccessBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySuccessBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar?.hide()

        val aniSlide: Animation = AnimationUtils.loadAnimation(applicationContext, R.anim.slide_up_400)
        val aniSlideUp: Animation = AnimationUtils.loadAnimation(applicationContext, R.anim.slide_up_delay_2)
        val expandUp: Animation = AnimationUtils.loadAnimation(applicationContext, R.anim.expand_up)

        binding.congratulationText.startAnimation(aniSlideUp)
        binding.yourCardText.startAnimation(aniSlideUp)
        binding.linearLayout.startAnimation(expandUp)
        binding.backWalletLayout.startAnimation(aniSlide)

        binding.backWalletLayout.setOnClickListener {
            finish()
        }

    }
}