package com.example.app2.mvvm.repository.api.model

import com.google.gson.annotations.SerializedName

class DogFact {
    @SerializedName("facts")
    var facts: List<String> = mutableListOf()

    @SerializedName("success")
    var success: Boolean = false
}