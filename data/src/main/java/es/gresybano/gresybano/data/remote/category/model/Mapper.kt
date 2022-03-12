package es.gresybano.gresybano.data.remote.category.model

import es.gresybano.gresybano.domain.entities.category.CategoryBo

fun CategoryDto.toBo() = CategoryBo(
    id = id ?: -1L,
    name = name ?: "",
    url = url ?: "",
)