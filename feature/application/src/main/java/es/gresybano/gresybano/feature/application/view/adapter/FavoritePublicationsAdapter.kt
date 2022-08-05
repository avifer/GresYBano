package es.gresybano.gresybano.feature.application.view.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import es.gresybano.gresybano.domain.category.entity.CategoryBo
import es.gresybano.gresybano.domain.publication.entity.PublicationBo
import es.gresybano.gresybano.feature.application.databinding.RowFavoritePublicationBinding
import es.gresybano.gresybano.feature.application.entity.FavoritePublicationVo


class FavoritePublicationsAdapter(
    private val listenerClickElement: (publication: PublicationBo) -> Unit,
    private val listenerClickSeeMore: (favoritePublicationVo: FavoritePublicationVo) -> Unit
) : ListAdapter<FavoritePublicationVo, RecyclerView.ViewHolder>(diffUtils) {

    class ViewHolderMoreSeenCategory(private val binding: RowFavoritePublicationBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(
            favoritePublicationVo: FavoritePublicationVo,
            listenerClickElement: (publication: PublicationBo) -> Unit,
            listenerClickSeeMore: (favoritePublicationVo: FavoritePublicationVo) -> Unit
        ) {
            with(binding.rowFavoritePublicationItem) {
                setTextHeader(favoritePublicationVo.nameCategory)
                setAdapter(
                    HeightPublicationAdapter(
                        listenerClickElement = {
                            listenerClickElement(it)
                        }
                    ).apply {
                        submitList(favoritePublicationVo.listPublications)
                    }
                )
                setOnClickSeeMore {
                    listenerClickSeeMore(favoritePublicationVo)
                }
            }
        }

    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        (holder as? ViewHolderMoreSeenCategory)?.bind(
            currentList[position],
            listenerClickElement,
            listenerClickSeeMore
        )
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolderMoreSeenCategory {
        return ViewHolderMoreSeenCategory(
            RowFavoritePublicationBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    fun setElements(list: List<PublicationBo>) {
        val listCategories = mutableListOf<CategoryBo>()
        list.forEach {
            listCategories.addAll(it.category)
        }
        submitList(
            listCategories.distinctBy { it.id }.map { category ->
                FavoritePublicationVo(
                    category.id,
                    category.name,
                    list.filter { publication ->
                        publication.category.map { it.id }.contains(category.id)
                    }
                )
            }
        )
    }

}

private val diffUtils = object : DiffUtil.ItemCallback<FavoritePublicationVo>() {
    override fun areItemsTheSame(
        oldItem: FavoritePublicationVo,
        newItem: FavoritePublicationVo
    ) = oldItem.idCategory == newItem.idCategory

    override fun areContentsTheSame(
        oldItem: FavoritePublicationVo,
        newItem: FavoritePublicationVo
    ) = oldItem.idCategory == newItem.idCategory
}