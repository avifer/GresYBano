package es.gresybano.gresybano.feature.onboarding.ui.view.vo

import es.gresybano.gresybano.domain.category.entity.CategoryBo

data class FavoriteCategoryVo(
    val id: Long,
    val tag: String?,
    val mainImage: String,
    val name: String,
    var selected: Boolean,
)

fun CategoryBo.toVo() = FavoriteCategoryVo(
    id = id,
    tag = tag,
    name = name,
    mainImage = mainImage,
    selected = false
)

fun FavoriteCategoryVo.toBo() = CategoryBo(
    id = id,
    tag = tag,
    name = name,
    mainImage = mainImage,
    isFavorite = selected,
)