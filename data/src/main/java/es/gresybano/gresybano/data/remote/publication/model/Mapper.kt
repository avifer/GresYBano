package es.gresybano.gresybano.data.remote.publication.model

import es.gresybano.gresybano.common.util.DEFAULT_ID_LONG
import es.gresybano.gresybano.domain.entities.PublicationBo
import java.util.*

fun PublicationDto.toBo() = PublicationBo(
    id = id ?: DEFAULT_ID_LONG,
    publishDate = parseDate(publishDate),
    category = listCategoriesId?.filterNotNull() ?: listOf(),
    listImages = listImages?.filterNotNull() ?: listOf(),
)

fun parseDate(dateString: String?) = Date()