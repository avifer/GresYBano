package es.gresybano.gresybano.domain.entities

data class HomeListElementsVo(
    val listCategories: List<CategoryBo>,
    val listMorePopularPublications: List<PublicationBo>,
    val listLastPublished: List<PublicationBo>,
)
