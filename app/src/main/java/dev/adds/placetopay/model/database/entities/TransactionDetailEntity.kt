package dev.adds.placetopay.model.database.entities

import androidx.room.Delete
import androidx.room.Embedded
import androidx.room.Relation

class TransactionDetailEntity (
    @Embedded
    val transaction: TransactionEntity,
    @Relation(
        parentColumn = "transactionId",
        entityColumn = "transactionOwnerId"
    )
    var status: StatusEntity,
    @Relation(
        parentColumn = "transactionId",
        entityColumn = "transactionOwnerId"
    )

    var payer: PayerEntity,
    @Relation(
        parentColumn = "transactionId",
        entityColumn = "transactionOwnerId"
    )
    var card: CardEntity,
    @Relation(
        parentColumn = "transactionId",
        entityColumn = "transactionOwnerId"
    )
    var amount: AmountEntity
)