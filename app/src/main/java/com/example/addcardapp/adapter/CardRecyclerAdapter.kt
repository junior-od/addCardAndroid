package com.example.addcardapp.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.RelativeLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.addcardapp.R
import com.example.addcardapp.models.CardDetails
import com.example.addcardapp.utils.Helper

class CardRecyclerAdapter( private var cardList: MutableList<CardDetails>) : RecyclerView.Adapter<CardRecyclerAdapter.CardsViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CardsViewHolder {
        return CardsViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.single_credit_card_layout, parent, false)
        )
    }

    override fun onBindViewHolder(holder: CardsViewHolder, position: Int) {
        val data = cardList[position]
        holder.bind(data, position)
    }

    override fun getItemCount(): Int {
        return cardList.size
    }

    class CardsViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var relativeLayout: RelativeLayout? = null
        var balance: TextView? = null
        var cardNumber: TextView? = null
        var cardExpiry: TextView? = null
        var cardTypeImage: ImageView? = null

        fun bind(data: CardDetails, position: Int) {
            if (position % 2 != 0 ) {
                relativeLayout?.setBackgroundResource(R.drawable.light_blue_cornered_bg)
            }else {
                relativeLayout?.setBackgroundResource(R.drawable.blue_cornered_bg)
            }
            cardExpiry?.text = data.expiry
            cardNumber?.text = data.cardNumber
            if (data.cardType.equals("visa")) {
                cardTypeImage?.setImageResource(R.drawable.ic_visa_2)
            }else {
                cardTypeImage?.setImageResource(R.drawable.ic_mc)
            }
            balance?.text = Helper.DontTrimCurrencyDecimalAndAttachCountrySymbol(data.balance)

        }

        init {
            relativeLayout = itemView.findViewById(R.id.relativeLayout)
            balance = itemView.findViewById(R.id.balance)
            cardNumber = itemView.findViewById(R.id.cardNumber)
            cardExpiry = itemView.findViewById(R.id.cardExpiry)
            cardTypeImage = itemView.findViewById(R.id.cardTypeImage)

        }
    }
}

