package es.gresybano.gresybano.feature.application.view.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import es.gresybano.gresybano.common.util.ZERO
import es.gresybano.gresybano.domain.category.entity.CategoryBo
import es.gresybano.gresybano.feature.application.databinding.RowAvailableCategoryNotificationBinding

class AvailableCategoriesNotificationsAdapter(
    private val listenerClickElement: (category: CategoryBo) -> Unit,
) : ListAdapter<CategoryBo, AvailableCategoriesNotificationsAdapter.ViewHolderAvailableCategory>(
    diffUtils
) {

    inner class ViewHolderAvailableCategory(
        private val binding: RowAvailableCategoryNotificationBinding,
    ) : RecyclerView.ViewHolder(binding.root) {

        init {
            binding.rowAvailableCategoryNotificationSwitchFavorite.setOnCheckedChangeListener { _, isChecked ->
                if (currentList[adapterPosition].isFavorite != isChecked) {
                    listenerClickElement(
                        currentList[adapterPosition].apply {
                            isFavorite = isChecked
                        }
                    )
                }
            }
        }

        fun bind(element: CategoryBo) {
            with(binding.rowAvailableCategoryNotificationSwitchFavorite) {
                text = element.name
                isChecked = element.isFavorite ?: false
            }
        }

    }

    override fun onBindViewHolder(holder: ViewHolderAvailableCategory, position: Int) {
        holder.bind(currentList[position])
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolderAvailableCategory {
        return ViewHolderAvailableCategory(
            RowAvailableCategoryNotificationBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    fun removeAllFavorites() {
        currentList.forEach { it.isFavorite = false }
        notifyItemRangeChanged(ZERO, currentList.size)
    }

    fun disableCategory(id: String) {
        currentList.find { it.id == id }?.isFavorite = false
        notifyItemRangeChanged(ZERO, currentList.size)
    }

}

private val diffUtils = object : DiffUtil.ItemCallback<CategoryBo>() {
    override fun areItemsTheSame(
        oldItem: CategoryBo,
        newItem: CategoryBo
    ) = oldItem.id == newItem.id &&
            oldItem.tag == newItem.tag &&
            oldItem.isFavorite == newItem.isFavorite

    override fun areContentsTheSame(
        oldItem: CategoryBo,
        newItem: CategoryBo
    ) = oldItem.id == newItem.id &&
            oldItem.tag == newItem.tag &&
            oldItem.isFavorite == newItem.isFavorite

}