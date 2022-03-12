package es.gresybano.gresybano.domain.entities.category

data class CategoryBo(
    val id: Long,
    val name: String,
    val url: String,
    var isFavorite: Boolean? = null,
)