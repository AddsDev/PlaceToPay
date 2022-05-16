package dev.adds.placetopay.provider

import dev.adds.placetopay.model.domain.ProductModel
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class ProductProvider @Inject constructor(){
    var productModels : List<ProductModel> = emptyList()
    var cart : MutableList<ProductModel> = mutableListOf()
}