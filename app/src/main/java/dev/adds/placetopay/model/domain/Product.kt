package dev.adds.placetopay.model.domain

import com.google.gson.annotations.SerializedName

data class Product(@SerializedName("id") var id: Long?,
                   var name: String?,
                   var img: String?,
                   var price: Float?)