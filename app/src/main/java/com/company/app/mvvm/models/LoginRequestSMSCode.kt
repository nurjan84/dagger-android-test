package com.company.app.mvvm.models

import com.google.gson.annotations.SerializedName

data class LoginRequestSMSCode(
    @SerializedName("cellPhone") var cellPhone: String
)