package es.gresybano.gresybano.feature.application.view.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import es.avifer.listheaderseemore.ListHeaderSeeMoreAdapter
import es.gresybano.gresybano.common.extensions.loadUrl
import es.gresybano.gresybano.domain.entities.CategoryBo
import es.gresybano.gresybano.feature.application.databinding.RowHeightCategoryBinding

class HeightCategoryAdapter(private val listenerClickElement: (category: CategoryBo) -> Unit) :
    ListHeaderSeeMoreAdapter<CategoryBo>(diffUtils) {

    class ViewHolderCategory(private val viewBinding: RowHeightCategoryBinding) :
        RecyclerView.ViewHolder(viewBinding.root) {

        fun bind(category: CategoryBo, listenerClickElement: (category: CategoryBo) -> Unit) {
            with(viewBinding) {
                setOnClickParent { listenerClickElement(category) }
                setData(category)
            }
        }

        private fun RowHeightCategoryBinding.setData(category: CategoryBo) {
            rowHeightCategoryImgImageCategory.loadUrl(category.primaryUrl, 150, 150)
            rowHeightCategoryLabelNameCategory.text = category.name
            rowHeightCategoryLabelAmountCategory.text = category.listPublications.size.toString()
        }

        private fun RowHeightCategoryBinding.setOnClickParent(action: () -> Unit) {
            root.setOnClickListener { action() }
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        (holder as? ViewHolderCategory)?.bind(currentList[position], listenerClickElement)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolderCategory {
        return ViewHolderCategory(
            RowHeightCategoryBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }
}

private val diffUtils = object : DiffUtil.ItemCallback<CategoryBo>() {
    override fun areItemsTheSame(
        oldItem: CategoryBo,
        newItem: CategoryBo
    ) = oldItem.id == newItem.id

    override fun areContentsTheSame(
        oldItem: CategoryBo,
        newItem: CategoryBo
    ) = oldItem.primaryUrl == newItem.primaryUrl && oldItem.name == newItem.name
}