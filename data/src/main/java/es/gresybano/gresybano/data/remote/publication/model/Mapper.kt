package es.gresybano.gresybano.data.remote.publication.model

import es.gresybano.gresybano.common.util.DEFAULT_ID_LONG
import es.gresybano.gresybano.domain.entities.PublicationBo
import java.util.*

fun PublicationDto.toBo() = PublicationBo(
    id = id ?: DEFAULT_ID_LONG,
    publishDate = publishDate ?: Date(),
    category = category ?: "",
    listImages = listImages?.filterNotNull() ?: listOf(),
)