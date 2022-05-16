package dev.adds.placetopay.util

object Constants {
    const val PRODUCT_MOCK_API = "https://6264408aa55d5055be46e83a.mockapi.io/"
    const val PLACE_TO_PAY_API = "https://dev.placetopay.com/rest/"
    const val PLACE_TO_PAY_LOGIN = "6dd490faf9cb87a9862245da41170ff2"
    const val PLACE_TO_PAY_KEY = "024h1IlD"
    const val API_DATE_FORMAT = "yyyy-MM-dd'T'HH:mmZ"
    const val ALGORITHM_API = "SHA-256"
    const val  SHOP_DATABASE_NAME = "shop"

    enum class StatusResponse(val status: String){
        OK("Ok"),
        FAILED("Failed")
    }

    enum class Currency(val currency: String){
        Dollar("USD"),
        Pesos_COP("COP")
    }
}