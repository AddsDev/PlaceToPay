package dev.adds.placetopay.model.database.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "status_transaction_table")
data class StatusEntity(
    @PrimaryKey(autoGenerate = true)
    var statusId: Long = 0,
    var status: String,
    var reason: String,
    var message: String,
    var date: String,
    val transactionOwnerId: Long) {
}