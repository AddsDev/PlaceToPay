package dev.adds.placetopay.provider

import dev.adds.placetopay.model.domain.Product

class ProductProvider {
    companion object{
        var products : List<Product> = emptyList()
        var cart : MutableList<Product> = mutableListOf()
    }
}