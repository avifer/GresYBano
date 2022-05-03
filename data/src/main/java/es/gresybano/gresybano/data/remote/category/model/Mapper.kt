package es.gresybano.gresybano.data.remote.category.model

import es.gresybano.gresybano.common.util.DEFAULT_ID_LONG
import es.gresybano.gresybano.domain.entities.CategoryBo

fun CategoryDto.toBo() = CategoryBo(
    id = id ?: DEFAULT_ID_LONG,
    name = name ?: "",
    primaryUrl = url ?: "",
)