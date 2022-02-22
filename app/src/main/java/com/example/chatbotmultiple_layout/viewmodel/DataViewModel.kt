package com.example.chatbotmultiple_layout.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.chatbotmultiple_layout.api.ServiceGenerator
import com.example.chatbotmultiple_layout.model.cancelordr
import com.example.chatbotmultiple_layout.model.ordrstatus
import retrofit2.Call
import retrofit2.Response

class DataViewModel(): ViewModel() {

    private var mutableLiveData= MutableLiveData<ordrstatus>()
    var statuslivedata: LiveData<ordrstatus> = mutableLiveData

    private var mutablecancelData= MutableLiveData<cancelordr>()
    var cancellivedata: LiveData<cancelordr> = mutablecancelData

    fun getorderstatus( accId: String, accesstoken: String, orderid: String ){


        ServiceGenerator.getapiservice().getdata(accId, accesstoken, orderid).
        enqueue(object: retrofit2.Callback<ordrstatus>{
            override fun onResponse(
                call: Call<ordrstatus>,
                response: Response<ordrstatus>
            ) {
                mutableLiveData.value= response.body()
                Log.d("dudi", "resp:${response.body()?.msg1} ")
            }

            override fun onFailure(call: Call<ordrstatus>, t: Throwable) {
                Log.d("dudi", "onStatusFailure: ")
            }

        })

    }

    fun getcancelstatus( accId: String, accesstoken: String, orderid: String ){


        ServiceGenerator.getapiservice().getstatus(accId, accesstoken, orderid)
            .enqueue(object : retrofit2.Callback<cancelordr>{
                override fun onResponse(
                    call: Call<cancelordr>,
                    response: Response<cancelordr>
                ) {
                    mutablecancelData.value= response.body()
                    Log.d("dudi", "cancel:${response.body()?.order_status} ")
                }

                override fun onFailure(call: Call<cancelordr>, t: Throwable) {
                    Log.d("dudi", "onCancelFailure: ")
                }

            })

    }
}