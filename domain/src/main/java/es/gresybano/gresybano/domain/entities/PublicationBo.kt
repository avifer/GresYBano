package es.gresybano.gresybano.domain.entities

import java.util.*

data class PublicationBo(
    val id: Long,
    val publishDate: Date = Date(),
    val category: List<Long>,
    val listImages: List<String>,
    val favorite: Boolean = false,
)