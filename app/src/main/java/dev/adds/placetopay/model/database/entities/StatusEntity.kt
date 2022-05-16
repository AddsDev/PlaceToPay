package dev.adds.placetopay.model.database.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "status_transaction_table")
data class StatusEntity(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id") var id: Long,
    @ColumnInfo(name = "status") var status: String, @ColumnInfo(name = "reason") var reason: String,
    @ColumnInfo(name = "message") var message: String, @ColumnInfo(name = "date") var date: String) {
}