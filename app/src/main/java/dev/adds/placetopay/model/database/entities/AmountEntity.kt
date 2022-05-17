package dev.adds.placetopay.model.database.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "total_payment_table")
data class AmountEntity(
    @PrimaryKey(autoGenerate = true)
    var amountId: Long = 0,
    var currency: String,
    var total: Int,
    val transactionOwnerId: Long)