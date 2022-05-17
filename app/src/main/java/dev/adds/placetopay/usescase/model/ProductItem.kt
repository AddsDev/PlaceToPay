package dev.adds.placetopay.usescase.model

import dev.adds.placetopay.model.database.entities.CartEntity
import dev.adds.placetopay.model.domain.ProductModel
import dev.adds.placetopay.usescase.converters.IConvertModel

data class ProductItem(var id: Long?,
                  var name: String?,
                  var img: String?,
                  var price: Float?) : IConvertModel<ProductModel> {
    override fun toModel(): ProductModel = ProductModel(id, name, img, price)
}

fun ProductModel.toDomain() = ProductItem(id, name, img, price)
fun CartEntity.toDomain() = ProductItem(id, name, img, price)
