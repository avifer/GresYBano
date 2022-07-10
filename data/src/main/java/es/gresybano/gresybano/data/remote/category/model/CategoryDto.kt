package es.gresybano.gresybano.data.remote.category.model

import com.google.gson.annotations.SerializedName

data class CategoryDto(
    @SerializedName("id")
    val id: Long?,
    @SerializedName("name")
    val name: String?,
    @SerializedName("mainImage")
    val url: String?,
)