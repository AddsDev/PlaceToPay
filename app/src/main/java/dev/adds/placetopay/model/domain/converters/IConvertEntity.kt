package dev.adds.placetopay.model.domain.converters

interface IConvertEntity<E> {
    fun toEntity() : E
}