package dev.adds.placetopay.model.database.entities

import androidx.room.*

@Entity(tableName = "transaction_table")
data class TransactionEntity(
    @PrimaryKey(autoGenerate = true)
    var transactionId: Long = 0,
    var provider: String?,
    var reference: String?)