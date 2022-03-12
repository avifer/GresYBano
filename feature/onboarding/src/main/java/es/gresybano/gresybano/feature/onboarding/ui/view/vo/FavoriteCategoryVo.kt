package es.gresybano.gresybano.feature.onboarding.ui.view.vo

import es.gresybano.gresybano.domain.entities.category.CategoryBo

data class FavoriteCategoryVo(
    val id: Long,
    val urlImage: String,
    val name: String,
    var selected: Boolean,
)

fun CategoryBo.toVo() = FavoriteCategoryVo(
    id = id,
    name = name,
    urlImage = url,
    selected = false
)

fun FavoriteCategoryVo.toBo() = CategoryBo(
    id = id,
    name = name,
    url = urlImage,
    isFavorite = selected
)