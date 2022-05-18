package dev.adds.placetopay.usescase.workers

import android.content.Context
import android.util.Log
import androidx.hilt.work.HiltWorker
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters
import dagger.assisted.Assisted
import dagger.assisted.AssistedInject
import dev.adds.placetopay.provider.repository.PaymentRepository
import dev.adds.placetopay.usescase.ManagementTransactions
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltWorker
class TransactionWorker @AssistedInject constructor(
    @Assisted context: Context,
    @Assisted params: WorkerParameters) : CoroutineWorker(context, params){
    companion object{
        const val WORK_TAG = "TRANSACTION_WORKER"
    }
    override suspend fun doWork(): Result = withContext(Dispatchers.IO){
        if(ManagementTransactions.pendingList.isNullOrEmpty())
            return@withContext Result.success()
        ManagementTransactions.pendingList.forEach {
            if(it.processResponseItem.statusItem.status != ManagementTransactions.tryTransaction(it)?.processResponseItem?.statusItem?.status){
                ManagementTransactions.pendingList.remove(it)
            }
        }
        return@withContext Result.retry()
    }

}