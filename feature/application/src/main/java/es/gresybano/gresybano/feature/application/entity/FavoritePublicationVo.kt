package es.gresybano.gresybano.feature.application.entity

import es.gresybano.gresybano.domain.publication.entity.PublicationBo

data class FavoritePublicationVo(
    val idCategory: String,
    val nameCategory: String,
    val mainImage: String,
    val listPublications: List<PublicationBo>,
)