package es.gresybano.gresybano.feature.application.entity

import es.gresybano.gresybano.domain.category.entity.CategoryBo
import es.gresybano.gresybano.domain.publication.entity.PublicationBo

data class HomeListElementsVo(
    val listCategories: List<CategoryBo>,
    val listMorePopularPublications: List<PublicationBo>,
    val listLastPublished: List<PublicationBo>,
)
