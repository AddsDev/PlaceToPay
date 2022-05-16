package dev.adds.placetopay.model.database.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "payer_table")
data class PayerEntity(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id") var id : Long,
    @ColumnInfo(name = "name")var name: String,
    @ColumnInfo(name = "surname") var surname : String,
    @ColumnInfo(name = "email") var email: String,
    @ColumnInfo(name = "documentType") var documentType: String,
    @ColumnInfo(name = "document")var document: String,
    @ColumnInfo(name = "mobile") var mobile: String) {
}