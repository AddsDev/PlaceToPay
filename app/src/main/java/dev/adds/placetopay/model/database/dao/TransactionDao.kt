package dev.adds.placetopay.model.database.dao

import androidx.room.*
import dev.adds.placetopay.model.database.entities.*

@Dao
interface TransactionDao{
    @Query("SELECT * FROM transaction_table")
    suspend fun getAllTransactions(): List<TransactionDetailEntity>

    @Query("SELECT * FROM transaction_table WHERE reference = :reference")
    suspend fun getTransactionByReference(reference: String): TransactionDetailEntity

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