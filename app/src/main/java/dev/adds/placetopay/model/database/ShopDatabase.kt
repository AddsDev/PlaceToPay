package dev.adds.placetopay.model.database

import androidx.room.Database
import androidx.room.RoomDatabase
import dev.adds.placetopay.model.database.dao.TransactionDao
import dev.adds.placetopay.model.database.entities.*

@Database(entities = [
    AmountEntity::class,
    CardEntity::class,
    PayerEntity::class,
    StatusEntity::class,
    TransactionEntity::class], version = 1)
abstract class ShopDatabase: RoomDatabase() {

    abstract fun getTransactionDao(): TransactionDao

}