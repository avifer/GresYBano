package es.gresybano.gresybano.domain.entities

import java.util.*

data class PublicationBo(
    val id: Long,
    val publishDate: Date = Date(),
    val category: String,
    val listImages: List<String>,
)