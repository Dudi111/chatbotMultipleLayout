package com.example.chatbotmultiple_layout.adapter

import androidx.recyclerview.widget.RecyclerView
import com.example.chatbotmultiple_layout.databinding.UserLayoutBinding
import com.example.chatbotmultiple_layout.views.Chatmessage

class userViewHolder(val view: UserLayoutBinding): RecyclerView.ViewHolder(view.root) {


    fun setdata(chatmessage: Chatmessage){
        view.apply {

            tvuser.text= chatmessage.msg4
        }
    }
}