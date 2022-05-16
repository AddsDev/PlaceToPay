package dev.adds.placetopay.provider

import dev.adds.placetopay.model.domain.Product
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class ProductProvider @Inject constructor(){
    var products : List<Product> = emptyList()
    var cart : MutableList<Product> = mutableListOf()
}