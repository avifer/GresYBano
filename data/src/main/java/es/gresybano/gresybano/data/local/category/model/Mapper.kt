package es.gresybano.gresybano.data.local.category.model

import es.gresybano.gresybano.domain.entities.category.CategoryBo

fun CategoryDbo.toBo() = CategoryBo(
    id = id,
    name = name,
    url = url,
    isFavorite = isFavorite
)

fun CategoryBo.toDbo() = CategoryDbo(
    id = id,
    name = name,
    url = url,
    isFavorite = isFavorite ?: false
)