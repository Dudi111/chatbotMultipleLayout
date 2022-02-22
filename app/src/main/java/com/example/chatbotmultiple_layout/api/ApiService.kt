package com.example.chatbotmultiple_layout.api

import com.example.chatbotmultiple_layout.extra.Constants.CANCEL_URL
import com.example.chatbotmultiple_layout.extra.Constants.END_URL
import com.example.chatbotmultiple_layout.model.cancelordr
import com.example.chatbotmultiple_layout.model.ordrstatus
import retrofit2.Call
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST

interface ApiService {


    @FormUrlEncoded
    @POST(END_URL)
    fun getdata(
        @Field("accessToken") accessToken: String,
        @Field("accountId") accountId: String,
        @Field("orderId") orderId: String,
    ): Call<ordrstatus>

    @FormUrlEncoded
    @POST(CANCEL_URL)
    fun getstatus(
        @Field("accountId") accountId: String?,
        @Field("accessToken") accessToken: String?,
        @Field("orderId") orderId: String?
    ): Call<cancelordr>
}