package es.gresybano.gresybano.domain.entities

data class CategoryBo(
    val id: Long,
    val name: String,
    val mainImage: String,
    val listPublications: List<PublicationBo> = listOf(),
    var isFavorite: Boolean? = null,
)