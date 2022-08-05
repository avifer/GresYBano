package es.gresybano.gresybano.data.local.favoritecategory.model

import es.gresybano.gresybano.domain.category.entity.CategoryBo
import es.gresybano.gresybano.domain.category.entity.FavoriteCategoryBo

fun FavoriteCategoryDbo.toBo() = FavoriteCategoryBo(
    id = id,
    tag = tag,
)

fun FavoriteCategoryBo.toDbo() = FavoriteCategoryDbo(
    id = id,
    tag = tag,
)

fun CategoryBo.toFavoriteCategoryDbo() = FavoriteCategoryDbo(
    id = id,
    tag = tag,
)