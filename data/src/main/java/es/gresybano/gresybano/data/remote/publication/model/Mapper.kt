package es.gresybano.gresybano.data.remote.publication.model

import es.gresybano.gresybano.domain.entities.PublicationBo
import java.util.*

fun PublicationDto.toBo() = PublicationBo(
    id = id ?: -1L,
    publishDate = publishDate ?: Date(),
    category = category ?: "",
    listImages = listImages?.filterNotNull() ?: listOf(),
)