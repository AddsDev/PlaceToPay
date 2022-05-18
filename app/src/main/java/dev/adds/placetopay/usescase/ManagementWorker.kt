package dev.adds.placetopay.usescase

import android.content.Context
import androidx.work.*
import dev.adds.placetopay.provider.repository.PaymentRepository
import dev.adds.placetopay.usescase.workers.TransactionWorker
import dev.adds.placetopay.util.Constants
import java.util.concurrent.TimeUnit
import javax.inject.Inject

class ManagementWorker @Inject constructor(val context: Context) {


    fun initWorker(){

        val period = PeriodicWorkRequestBuilder<TransactionWorker>(
            repeatInterval = Constants.TIME_UPDATE_API,
            repeatIntervalTimeUnit = TimeUnit.MINUTES
        ).setConstraints(
            Constraints.Builder()
                .setRequiredNetworkType(NetworkType.CONNECTED)
                .build()
        )

        WorkManager.getInstance(context).enqueueUniquePeriodicWork(
            TransactionWorker.WORK_TAG,
            ExistingPeriodicWorkPolicy.REPLACE,
            period.build()
        )
    }


}