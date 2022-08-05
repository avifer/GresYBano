package es.gresybano.gresybano.data.local.favoritepublication.model

import es.gresybano.gresybano.domain.publication.entity.FavoritePublicationBo
import es.gresybano.gresybano.domain.publication.entity.PublicationBo

fun FavoritePublicationDbo.toBo() = FavoritePublicationBo(
    id = id,
)

fun FavoritePublicationBo.toDbo() = FavoritePublicationDbo(
    id = id,
)

fun PublicationBo.toFavoritePublicationDbo() = FavoritePublicationDbo(
    id = id,
)