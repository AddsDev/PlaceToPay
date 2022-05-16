package dev.adds.placetopay.model.domain

import com.google.gson.annotations.SerializedName
import dev.adds.placetopay.model.domain.payment.ProcessResponse
import dev.adds.placetopay.model.domain.payment.Process

data class Shopping(@SerializedName("process")var process: Process,@SerializedName("processResponse") var processResponse: ProcessResponse)