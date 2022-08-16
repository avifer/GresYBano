package es.gresybano.gresybano.domain.category.entity

import es.gresybano.gresybano.domain.publication.entity.PublicationBo

data class CategoryBo(
    val id: String,
    val tag: String? = null,
    val name: String,
    val mainImage: String,
    val listPublications: List<PublicationBo> = listOf(),
    var isFavorite: Boolean? = null,
)