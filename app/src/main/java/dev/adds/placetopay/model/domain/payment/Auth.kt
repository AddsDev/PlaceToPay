package dev.adds.placetopay.model.domain.payment

import com.google.gson.annotations.SerializedName
import dev.adds.placetopay.util.Constants
import dev.adds.placetopay.util.extension.apiFormat
import dev.adds.placetopay.util.extension.getBase64
import dev.adds.placetopay.util.extension.getRandom
import dev.adds.placetopay.util.extension.getSHA256
import java.util.*

class Auth(@SerializedName("login") var login: String, key: String){
    @SerializedName("seed") var seed: String
    @SerializedName("nonce") var nonce: String
    @SerializedName("tranKey") var tranKey : String

    init {
        val nonceTemp = String().getRandom()
        seed = Date().apiFormat()
        tranKey = String().getBase64(String().getSHA256(nonceTemp+seed+key))
        nonce = String().getBase64(nonceTemp.encodeToByteArray())
    }
}