package es.gresybano.gresybano.data.remote.publication.model

import com.google.gson.annotations.SerializedName
import java.util.*

data class PublicationDto(
    @SerializedName("id")
    val id: Long?,
    @SerializedName("publishDate")
    val publishDate: Date?,
    @SerializedName("category")
    val category: String?,
    @SerializedName("listImages")
    val listImages: List<String?>?,
)