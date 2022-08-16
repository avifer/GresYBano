package es.gresybano.gresybano.data.remote.category.model

import es.gresybano.gresybano.common.util.EMPTY_STRING
import es.gresybano.gresybano.data.remote.publication.model.toBo
import es.gresybano.gresybano.domain.category.entity.CategoryBo

fun CategoryDto.toBo() = CategoryBo(
    id = id ?: EMPTY_STRING,
    tag = tag,
    name = name ?: EMPTY_STRING,
    mainImage = url ?: EMPTY_STRING,
    listPublications = listPublications?.mapNotNull { it?.toBo() } ?: emptyList()
)