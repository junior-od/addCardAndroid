package com.example.addcardapp

import android.animation.ValueAnimator
import android.os.Bundle
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import androidx.appcompat.app.AppCompatActivity
import com.example.addcardapp.databinding.ActivityMainBinding
import com.example.addcardapp.utils.Helper


class MainActivity : AppCompatActivity() {
    private lateinit var binding : ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar?.hide()

        val aniSlide: Animation = AnimationUtils.loadAnimation(applicationContext, R.anim.slide_up)
        binding.addCardLayout.startAnimation(aniSlide);

        animateBalance(40332233)

    }

    private fun animateBalance(newbalance: Int) {
        val animator = ValueAnimator()
        animator.setObjectValues(0, newbalance)
        animator.addUpdateListener {

            binding.availableBalance.text = Helper.DontTrimCurrencyDecimalAndAttachCountrySymbol(
                it.animatedValue.toString()
            )
        }
        animator.duration = 2000 // here you set the duration of the anim

        animator.start()


    }
}