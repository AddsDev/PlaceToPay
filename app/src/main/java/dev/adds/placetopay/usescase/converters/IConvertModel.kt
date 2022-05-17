package dev.adds.placetopay.usescase.converters

interface IConvertModel<IModel> {
    fun toModel() : dev.adds.placetopay.model.domain.IModel
}

interface ICovertDomain<E> {
    fun toDomain() : E
}