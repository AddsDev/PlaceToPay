package dev.adds.placetopay.model.domain

import com.google.gson.annotations.SerializedName

data class PayerModel(@SerializedName("name") var name: String,
                      @SerializedName("surname") var surname : String,
                      @SerializedName("email") var email: String,
                      @SerializedName("documentType") var documentType: String,
                      @SerializedName("document") var document: String,
                      @SerializedName("mobile") var mobile: String) : IModel