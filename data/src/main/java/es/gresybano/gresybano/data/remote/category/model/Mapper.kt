package es.gresybano.gresybano.data.remote.category.model

import es.gresybano.gresybano.common.util.DEFAULT_ID_LONG
import es.gresybano.gresybano.common.util.EMPTY_STRING
import es.gresybano.gresybano.domain.category.entity.CategoryBo

fun CategoryDto.toBo() = CategoryBo(
    id = id ?: DEFAULT_ID_LONG,
    name = name ?: EMPTY_STRING,
    mainImage = url ?: EMPTY_STRING,
)