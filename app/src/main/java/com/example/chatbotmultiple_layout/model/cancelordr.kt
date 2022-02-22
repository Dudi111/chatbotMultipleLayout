package com.example.chatbotmultiple_layout.model

data class cancelordr(
    val accessToken: String,
    val accountId: String,
    val error: Boolean,
    val error_code: Int,
    val orderId: String,
    val order_status: String
)