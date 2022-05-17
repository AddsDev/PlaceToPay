package dev.adds.placetopay.usescase.crud

interface IAddItem<E> {
    suspend fun addItem(item: E): Unit
}

interface IRemoveItem<E>{
    suspend fun removeItem(item: E): Boolean
}

interface IGetItemsWithParameter<E>{
    fun getAllItem(item: E): List<E>
}
interface IGetItems<E>{
    fun getAllItems(): List<E>
}
interface IGetItemWithParameter<E>{
    fun getItem(item: E): E
}
interface IGetItem<E>{
    fun getItem(): E
}
