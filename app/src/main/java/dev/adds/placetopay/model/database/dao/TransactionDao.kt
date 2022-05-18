package dev.adds.placetopay.model.database.dao

import androidx.room.*
import dev.adds.placetopay.model.database.entities.*
import dev.adds.placetopay.util.Constants

@Dao
interface TransactionDao{
    @Query("SELECT * FROM transaction_table ORDER BY transactionId DESC")
    suspend fun getAllTransactions(): List<TransactionDetailEntity>

    @Query("SELECT * FROM transaction_table as t "+
            "JOIN status_transaction_table s ON t.transactionId = s.transactionOwnerId " +
            "JOIN card_payment_table cd ON t.transactionId = cd.transactionOwnerId " +
            "JOIN payer_table py ON t.transactionId = py.transactionOwnerId " +
            "JOIN total_payment_table tot ON t.transactionId = tot.transactionOwnerId " +
            "WHERE s.status IN('PENDING','PENDING_VALIDATION','PENDING_PROCESS')")
    suspend fun getAllPendingTransactions(): List<TransactionDetailEntity>?

    @Query("SELECT * FROM transaction_table WHERE internalReference == :internalReference")
    suspend fun getTransactionByReference(internalReference: Long): TransactionDetailEntity?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(statusEntity: StatusEntity): Long

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(payerEntity: PayerEntity): Long

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(cardEntity: CardEntity): Long

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(amountEntity: AmountEntity): Long

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(transaction: TransactionEntity): Long

    @Delete
    suspend fun remove(vararg transaction: TransactionEntity) : Int
}