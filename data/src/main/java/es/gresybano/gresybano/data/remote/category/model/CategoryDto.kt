package es.gresybano.gresybano.data.remote.category.model

import com.google.gson.annotations.SerializedName
import es.gresybano.gresybano.data.remote.publication.model.PublicationDto

data class CategoryDto(
    @SerializedName("id")
    val id: Long?,
    @SerializedName("tag")
    val tag: String?,
    @SerializedName("name")
    val name: String?,
    @SerializedName("mainImage")
    val url: String?,
    @SerializedName("listPublications")
    val listPublications: List<PublicationDto?>?,
)