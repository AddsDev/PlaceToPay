package dev.adds.placetopay.model.domain.payment

import dev.adds.placetopay.util.Constants
import dev.adds.placetopay.util.extension.apiFormat
import dev.adds.placetopay.util.extension.getBase64
import dev.adds.placetopay.util.extension.getRandom
import dev.adds.placetopay.util.extension.getSHA256
import java.util.*

class Auth(var login: String, key: String){
    var seed: String
    var nonce: String
    var tranKey : String

    init {
        val nonceTemp = String().getRandom()
        seed = Date().apiFormat()
        tranKey = String().getBase64(String().getSHA256(nonceTemp+seed+key))
        nonce = String().getBase64(nonceTemp.encodeToByteArray())
    }
}