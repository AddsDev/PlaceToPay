package dev.adds.placetopay.model.database.dao

import androidx.room.*
import dev.adds.placetopay.model.database.entities.CartEntity

@Dao
interface CartDao {
    @Query("SELECT * FROM cart_table")
    suspend fun getAllProducts(): List<CartEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(cartEntity: CartEntity): Long

    @Delete
    suspend fun remove(vararg cartEntity: CartEntity): Int

    @Query("DELETE FROM cart_table")
    suspend fun remove(): Int
}