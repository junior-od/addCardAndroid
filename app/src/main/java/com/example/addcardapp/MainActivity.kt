package com.example.addcardapp

import android.os.Bundle
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import androidx.appcompat.app.AppCompatActivity
import androidx.viewpager.widget.ViewPager
import com.example.addcardapp.databinding.ActivityMainBinding
import com.example.addcardapp.fragments.DashboardFragment
import com.example.addcardapp.viewpager.AddCardViewPagerAdapter


class MainActivity : AppCompatActivity() {
    private lateinit var binding : ActivityMainBinding
    private var addCardViewPagerAdapter : AddCardViewPagerAdapter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar?.hide()

        val aniSlide: Animation = AnimationUtils.loadAnimation(applicationContext, R.anim.slide_up)
        val aniSlideDelayed: Animation = AnimationUtils.loadAnimation(applicationContext, R.anim.slide_up_delayed)

        addCardViewPagerAdapter = AddCardViewPagerAdapter(supportFragmentManager)
        addCardViewPagerAdapter?.addFragment(DashboardFragment(), "")
        binding.viewPager.adapter = addCardViewPagerAdapter
        binding.viewPager.offscreenPageLimit = addCardViewPagerAdapter?.count!!

        binding.bottomNavigationLayout.startAnimation(aniSlide)
        binding.fabIc.startAnimation((aniSlideDelayed))


    }
}