package dev.adds.placetopay.model.database.entities

import androidx.room.ColumnInfo
import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "transaction_table")
data class TransactionEntity(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id") var id: Long,
    @Embedded
    @ColumnInfo(name = "status") var statusEntity: StatusEntity,
    @ColumnInfo(name = "provider") var provider: String,
    @ColumnInfo(name = "reference") var reference: String,
    @Embedded
    @ColumnInfo(name = "payer") var payerEntity: PayerEntity,
    @Embedded
    @ColumnInfo(name = "card") var cardEntity: CardEntity,
    @Embedded
    @ColumnInfo(name = "amount") var amountEntity: AmountEntity) {
}