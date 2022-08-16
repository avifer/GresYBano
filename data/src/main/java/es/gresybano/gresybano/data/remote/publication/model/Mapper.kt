package es.gresybano.gresybano.data.remote.publication.model

import es.gresybano.gresybano.common.util.EMPTY_STRING
import es.gresybano.gresybano.domain.publication.entity.PublicationBo
import java.util.*

fun PublicationDto.toBo() = PublicationBo(
    id = id ?: EMPTY_STRING,
    publishDate = parseDate(publishDate),
    category = listCategories?.filterNotNull() ?: listOf(),
    listImages = listImages?.filterNotNull() ?: listOf(),
)

fun parseDate(dateString: Long?) = dateString?.let { Date(it) } ?: Date()