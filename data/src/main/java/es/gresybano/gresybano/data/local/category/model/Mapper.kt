package es.gresybano.gresybano.data.local.category.model

import es.gresybano.gresybano.domain.entities.CategoryBo

fun CategoryDbo.toBo() = CategoryBo(
    id = id,
    name = name,
    mainImage = url,
    isFavorite = isFavorite,
)

fun CategoryBo.toDbo() = CategoryDbo(
    id = id,
    name = name,
    url = mainImage,
    isFavorite = isFavorite ?: false
)