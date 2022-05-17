package dev.adds.placetopay.model.domain

import com.google.gson.annotations.SerializedName

data class ProductModel(@SerializedName("id") var id: Long?,
                        @SerializedName("name")var name: String?,
                        @SerializedName("img")var img: String?,
                        @SerializedName("price")var price: Float?) : IModel