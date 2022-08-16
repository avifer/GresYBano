package es.gresybano.gresybano.data.remote.publication.model

import com.google.gson.annotations.SerializedName
import es.gresybano.gresybano.domain.category.entity.CategoryBo

data class PublicationDto(
    @SerializedName("id")
    val id: String?,
    @SerializedName("publishDate")
    val publishDate: Long?,
    @SerializedName("listCategories")
    val listCategories: List<CategoryBo?>?,
    @SerializedName("listImages")
    val listImages: List<String?>?,
)