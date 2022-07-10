package es.gresybano.gresybano.data.remote.publication.model

import com.google.gson.annotations.SerializedName
import java.util.*

data class PublicationDto(
    @SerializedName("id")
    val id: Long?,
    @SerializedName("publishDate")
    val publishDate: String?,
    @SerializedName("listCategoriesId")
    val listCategoriesId: List<Long?>?,
    @SerializedName("listImages")
    val listImages: List<String?>?,
)