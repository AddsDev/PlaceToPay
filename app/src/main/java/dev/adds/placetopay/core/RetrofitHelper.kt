package dev.adds.placetopay.core

import dev.adds.placetopay.util.Constants
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

enum class UrlApi(val url : String){
    PLACE_TO_PAY(Constants.PLACE_TO_PAY_API),
    PRODUCT(Constants.PRODUCT_MOCK_API)
}
object RetrofitHelper {
    fun getRetrofit(api: UrlApi):Retrofit{
        return Retrofit.Builder()
            .baseUrl(api.url)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }
}