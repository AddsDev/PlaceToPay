package dev.adds.placetopay.model.database.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "payer_table")
data class PayerEntity(
    @PrimaryKey(autoGenerate = true)
    var payerId : Long = 0,
    var name: String,
    var surname : String,
    var email: String,
    var documentType: String,
    var document: String,
    var mobile: String, val transactionOwnerId: Long) {
}