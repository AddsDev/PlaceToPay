package dev.adds.placetopay.model.database.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "card_payment_table")
data class CardEntity(
    @PrimaryKey(autoGenerate = true)
    var cardId: Long = 0,
    var number: String?,
    var expiration: String?,
    val transactionOwnerId: Long)