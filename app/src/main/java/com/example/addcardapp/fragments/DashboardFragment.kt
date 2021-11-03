package com.example.addcardapp.fragments

import android.animation.ValueAnimator
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.addcardapp.R
import com.example.addcardapp.utils.Helper
import kotlinx.android.synthetic.main.fragment_dashboard.*

class DashboardFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_dashboard, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        animateBalance(400030333)
    }

    private fun animateBalance(newbalance: Int) {
        val animator = ValueAnimator()
        animator.setObjectValues(0, newbalance)
        animator.addUpdateListener {

            availableBalance.text = Helper.DontTrimCurrencyDecimalAndAttachCountrySymbol(
                it.animatedValue.toString()
            )
        }
        animator.duration = 2000 // here you set the duration of the anim

        animator.start()


    }
}