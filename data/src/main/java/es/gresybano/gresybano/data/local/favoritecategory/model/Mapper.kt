package es.gresybano.gresybano.data.local.favoritecategory.model

import es.gresybano.gresybano.domain.category.entity.CategoryBo

fun FavoriteCategoryDbo.toBo() = CategoryBo(
    id = id,
    name = name,
    mainImage = mainImage,
    isFavorite = isFavorite,
)

fun CategoryBo.toDbo() = FavoriteCategoryDbo(
    id = id,
    name = name,
    mainImage = mainImage,
    isFavorite = isFavorite ?: false
)