package dev.adds.placetopay.util.extension

import dev.adds.placetopay.util.Constants
import java.text.SimpleDateFormat
import java.util.*

fun Date.apiFormat():String = SimpleDateFormat(Constants.API_DATE_FORMAT,
    Locale.getDefault()).format(Date())
