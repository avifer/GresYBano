package es.gresybano.gresybano.domain.splash.entity

data class VersionControlBo(
    val minVersion: String,
    val title: String,
    val description: String,
    val textButtonAccept: String,
    val textButtonCancel: String,
)