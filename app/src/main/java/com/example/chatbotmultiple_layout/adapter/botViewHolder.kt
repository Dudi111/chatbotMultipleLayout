package com.example.chatbotmultiple_layout.adapter

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.example.chatbotmultiple_layout.databinding.BotLayoutBinding
import com.example.chatbotmultiple_layout.extra.Constants
import com.example.chatbotmultiple_layout.views.Chatmessage
import com.example.chatbotmultiple_layout.views.Clicklistener

class botViewHolder(val view: BotLayoutBinding, val clicklistener: Clicklistener): RecyclerView.ViewHolder(view.root) {


    fun setdata(chatmessage: Chatmessage) {
        view.apply {
            if (chatmessage.msg1 != null) {
                tvbot1.text = chatmessage.msg1
            } else {
                tvbot1.visibility = View.GONE
            }
            if (chatmessage.msg1 != null) {
                tvbot2.text = chatmessage.msg2
            } else {
                tvbot2.visibility = View.GONE
            }
            if (chatmessage.msg1 != null) {
                tvbot3.text = chatmessage.msg3
            } else {
                tvbot2.visibility = View.GONE
            }

            btnCancel.setOnClickListener {
                clicklistener.onCancelClick()
            }
            btnStatus.setOnClickListener {
                clicklistener.onSubmitClick()
            }

            if (Constants.cancelclickcount >= 1 || Constants.submitclickcount >= 1) {
                btnCancel.visibility = View.GONE
                btnStatus.visibility = View.GONE
                btnCancel.isClickable = false
                btnStatus.isClickable = false
                //   Constants.cancelclickcount=0
                //  Constants.submitclickcount=0

            }
        }
    }
}