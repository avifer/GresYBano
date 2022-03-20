package es.gresybano.gresybano.data.remote.category.model

import es.gresybano.gresybano.domain.entities.CategoryBo

fun CategoryDto.toBo() = CategoryBo(
    id = id ?: -1L,
    name = name ?: "",
    primaryUrl = url ?: "",
)