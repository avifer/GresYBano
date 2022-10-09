package es.gresybano.gresybano.data.remote.splash.model

import es.gresybano.gresybano.domain.splash.entity.VersionControlBo

fun VersionControlDto.toBo() = VersionControlBo(
    minVersion = minVersion,
    title = title,
    description = description,
    textButtonAccept = textButtonAccept,
    textButtonCancel = textButtonCancel,
)