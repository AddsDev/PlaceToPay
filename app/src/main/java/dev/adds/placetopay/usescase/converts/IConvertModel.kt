package dev.adds.placetopay.usescase.converts

import dev.adds.placetopay.model.domain.IModel

interface IConvertModel<IModel> {
    fun toModel() : dev.adds.placetopay.model.domain.IModel
}

interface ICovertDomain<E> {
    fun toDomain() : E
}

interface ICovertEntity<E> {
    fun toEntity() : E
}