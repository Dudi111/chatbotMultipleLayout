package com.example.chatbotmultiple_layout.adapter

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.chatbotmultiple_layout.R
import com.example.chatbotmultiple_layout.databinding.BotLayoutBinding
import com.example.chatbotmultiple_layout.databinding.UserLayoutBinding
import com.example.chatbotmultiple_layout.views.Chatmessage
import com.example.chatbotmultiple_layout.views.Clicklistener

class DataAdapter(val context: Context, private var dataList : List<Chatmessage>, val clicklistener: Clicklistener) : RecyclerView.Adapter<RecyclerView.ViewHolder>(){




    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val view: View


        Log.d("dudi", "onCreateViewHolder:${viewType} ")
        return if(viewType==0) {

            val view = LayoutInflater.from(context)
            val inflater: BotLayoutBinding =
                DataBindingUtil.inflate(view, R.layout.bot_layout, parent, false)
            return botViewHolder(inflater, clicklistener)
        }else
        {
            val view = LayoutInflater.from(context)
            val userinflater: UserLayoutBinding =
                DataBindingUtil.inflate(view,R.layout.user_layout, parent, false)
            return userViewHolder(userinflater)
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {


        val viewtype: Int = getItemViewType(position)
        Log.d("dudi", "onBindViewHolder:${viewtype} ")
        if (viewtype==0) {

            val senderText: Chatmessage = dataList[position]
            (holder as botViewHolder).setdata(senderText)
        }else
        {
            val receiverModel: Chatmessage = dataList[position]
            (holder as userViewHolder).setdata(receiverModel)
        }


    }

    override fun getItemCount(): Int {
        return dataList.size
    }


    override fun getItemViewType(position: Int): Int {

        if (dataList[position].msg4 != null){
            return 1
        }else{
            return 0
        }
    }


}