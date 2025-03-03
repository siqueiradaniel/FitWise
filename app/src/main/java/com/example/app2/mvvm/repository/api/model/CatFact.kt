package com.example.app2.mvvm.repository.api.model

import com.google.gson.annotations.SerializedName

class CatFact {
    @SerializedName("fact")
    var fact: String = ""

    @SerializedName("length")
    var length: Int = 0
}