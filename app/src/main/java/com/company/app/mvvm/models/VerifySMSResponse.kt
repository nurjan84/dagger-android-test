package com.company.app.mvvm.models

import com.google.gson.annotations.SerializedName


data class VerifySMSResponse(
    @SerializedName("id") val id: Int, //1150
    @SerializedName("key") val key: String //f08c198a271f754e9e3b17aa05515aa6
)