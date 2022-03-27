package es.gresybano.gresybano.data.remote.publication.model

import es.gresybano.gresybano.common.util.DEFAULT_ID
import es.gresybano.gresybano.domain.entities.PublicationBo
import java.util.*

fun PublicationDto.toBo() = PublicationBo(
    id = id ?: DEFAULT_ID,
    publishDate = publishDate ?: Date(),
    category = category ?: "",
    listImages = listImages?.filterNotNull() ?: listOf(),
)