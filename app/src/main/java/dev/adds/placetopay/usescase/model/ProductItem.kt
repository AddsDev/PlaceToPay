package dev.adds.placetopay.usescase.model

import com.google.gson.annotations.SerializedName
import dev.adds.placetopay.model.domain.IModel
import dev.adds.placetopay.model.domain.ProductModel
import dev.adds.placetopay.usescase.converts.IConvertModel

data class ProductItem(var id: Long?,
                  var name: String?,
                  var img: String?,
                  var price: Float?) : IConvertModel<ProductModel> {
    override fun toModel(): ProductModel = ProductModel(id, name, img, price)
}

fun ProductModel.toDomain() = ProductItem(id, name, img, price)
