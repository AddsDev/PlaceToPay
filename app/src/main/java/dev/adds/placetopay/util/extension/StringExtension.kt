package dev.adds.placetopay.util.extension

import android.os.Build
import dev.adds.placetopay.util.Constants
import java.math.BigInteger
import java.security.MessageDigest
import java.security.SecureRandom
import java.util.*

fun String.getRandom(): String = BigInteger(130, SecureRandom()).toString()

fun String.getBase64(input: ByteArray) : String{
    val encodedBytes: ByteArray = if (Build.VERSION.SDK_INT >=
        Build.VERSION_CODES.O) {
        Base64.getEncoder().encode(input)
    } else {
        android.util.Base64.encode(input, android.util.Base64.NO_WRAP)
    }
    return String(encodedBytes)
}

fun String.getSHA256(input: String) : ByteArray{
    val mDigest: MessageDigest = MessageDigest.getInstance(Constants.ALGORITHM_API)
    return mDigest.digest(input.toByteArray())
}