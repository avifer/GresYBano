package es.gresybano.gresybano.domain.publication.entity

import es.gresybano.gresybano.domain.category.entity.CategoryBo
import java.util.*

data class PublicationBo(
    val id: String,
    val publishDate: Date = Date(),
    val category: List<CategoryBo>,
    val listImages: List<String>,
    var favorite: Boolean = false,
)