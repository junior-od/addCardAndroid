package com.example.addcardapp.fragments

import android.animation.ValueAnimator
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.addcardapp.R
import com.example.addcardapp.adapter.CardRecyclerAdapter
import com.example.addcardapp.models.CardDetails
import com.example.addcardapp.utils.Helper
import kotlinx.android.synthetic.main.fragment_dashboard.*

class DashboardFragment : Fragment() {

    private var cardRecyclerAdapter: CardRecyclerAdapter? = null
    private var dataList : MutableList<CardDetails> = ArrayList()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_dashboard, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val cardDetails1 = CardDetails("400534.67","12/32","**** 4456","mc")

        val cardDetails2 = CardDetails("60734.87","12/38","**** 9803","visa")

        dataList.add(cardDetails1)
        dataList.add(cardDetails2)

        cardRecyclerView?.layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
        cardRecyclerAdapter = CardRecyclerAdapter(dataList)
        cardRecyclerView?.adapter =  cardRecyclerAdapter

        val aniSlide: Animation = AnimationUtils.loadAnimation(context, R.anim.slide_up)
        val aniSlideLeft: Animation = AnimationUtils.loadAnimation(context, R.anim.slide_left)

        cardRecyclerView.startAnimation(aniSlideLeft)

        addCardLayout.startAnimation(aniSlide)

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