package es.gresybano.gresybano.data.remote.publication.model

import com.google.gson.annotations.SerializedName
import es.gresybano.gresybano.domain.category.entity.CategoryBo

data class PublicationDto(
    @SerializedName("id")
    val id: Long?,
    @SerializedName("publishDate")
    val publishDate: String?,
    @SerializedName("listCategories")
    val listCategories: List<CategoryBo?>?,
    @SerializedName("listImages")
    val listImages: List<String?>?,
)