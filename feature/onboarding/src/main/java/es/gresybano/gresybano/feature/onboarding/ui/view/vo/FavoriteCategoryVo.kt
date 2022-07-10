package es.gresybano.gresybano.feature.onboarding.ui.view.vo

import es.gresybano.gresybano.domain.entities.CategoryBo

data class FavoriteCategoryVo(
    val id: Long,
    val mainImage: String,
    val name: String,
    var selected: Boolean,
)

fun CategoryBo.toVo() = FavoriteCategoryVo(
    id = id,
    name = name,
    mainImage = mainImage,
    selected = false
)

fun FavoriteCategoryVo.toBo() = CategoryBo(
    id = id,
    name = name,
    mainImage = mainImage,
    isFavorite = selected,
)