package dev.adds.placetopay.model.database.entities

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity(tableName = "cart_table")
class CartEntity(
    @PrimaryKey(autoGenerate = true)
    var id: Long = 0,
    var name: String?,
    var img: String?,
    var price: Float?
)