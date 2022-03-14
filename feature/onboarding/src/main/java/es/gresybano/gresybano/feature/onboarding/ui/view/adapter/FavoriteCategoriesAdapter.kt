package es.gresybano.gresybano.feature.onboarding.ui.view.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import es.gresybano.gresybano.common.extensions.getColorState
import es.gresybano.gresybano.common.extensions.getDrawableCompat
import es.gresybano.gresybano.common.extensions.loadUrl
import es.gresybano.gresybano.feature.onboarding.R
import es.gresybano.gresybano.feature.onboarding.databinding.RowFavoriteCategoryBinding
import es.gresybano.gresybano.feature.onboarding.ui.view.vo.FavoriteCategoryVo

class FavoriteCategoriesAdapter(private val listenerClickElement: () -> Unit) :
    ListAdapter<FavoriteCategoryVo, FavoriteCategoriesAdapter.ViewHolderFavoriteCategory>(diffUtils) {

    class ViewHolderFavoriteCategory(private val viewBinding: RowFavoriteCategoryBinding) :
        RecyclerView.ViewHolder(viewBinding.root) {

        fun bind(category: FavoriteCategoryVo, listenerClickElement: () -> Unit) {
            with(viewBinding) {
                setOnClickParent {
                    category.selected = !category.selected
                    listenerClickElement()
                    category.selected
                }
                setData(category)
            }
        }

        private fun RowFavoriteCategoryBinding.setData(category: FavoriteCategoryVo) {
            rowFavoriteCategoryImgCategory.loadUrl(category.urlImage, 150, 130)
            rowFavoriteCategoryLabelNameCategory.text = category.name
        }

        private fun RowFavoriteCategoryBinding.setOnClickParent(action: () -> Boolean) {
            rowFavoriteCategoryCardParent.setOnClickListener {
                if (action()) {
                    selectRow()

                } else {
                    unselectRow()
                }
            }
        }

        private fun RowFavoriteCategoryBinding.selectRow() {
            with(root.context) {
                rowFavoriteCategoryCardParent.backgroundTintList =
                    getColorState(es.gresybano.gresybano.common.R.color.secondary_color)

                rowFavoriteCategoryViewSelected.background =
                    getDrawableCompat(R.drawable.row_favorite_category__background_selected)
            }
        }

        private fun RowFavoriteCategoryBinding.unselectRow() {
            rowFavoriteCategoryCardParent.backgroundTintList =
                root.context.getColorState(es.gresybano.gresybano.common.R.color.white)

            rowFavoriteCategoryViewSelected.background = null
        }
    }

    override fun onBindViewHolder(holder: ViewHolderFavoriteCategory, position: Int) =
        holder.bind(currentList[position], listenerClickElement)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolderFavoriteCategory {
        return ViewHolderFavoriteCategory(
            RowFavoriteCategoryBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }
}

private val diffUtils = object : DiffUtil.ItemCallback<FavoriteCategoryVo>() {
    override fun areItemsTheSame(
        oldItem: FavoriteCategoryVo,
        newItem: FavoriteCategoryVo
    ) = oldItem.id == newItem.id

    override fun areContentsTheSame(
        oldItem: FavoriteCategoryVo,
        newItem: FavoriteCategoryVo
    ) = oldItem.urlImage == newItem.urlImage && oldItem.name == newItem.name
}