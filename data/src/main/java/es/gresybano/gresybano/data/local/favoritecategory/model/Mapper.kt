package es.gresybano.gresybano.data.local.favoritecategory.model

import es.gresybano.gresybano.domain.category.entity.CategoryBo

fun FavoriteCategoryDbo.toBo() = CategoryBo(
    id = id,
    name = name,
    mainImage = mainImage,
    isFavorite = true,
)

fun CategoryBo.toDbo() = FavoriteCategoryDbo(
    id = id,
    tag = tag,
    name = name,
    mainImage = mainImage,
)