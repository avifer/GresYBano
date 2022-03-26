package es.gresybano.gresybano.feature.application.view.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import es.avifer.listheaderseemore.ListHeaderSeeMoreAdapter
import es.gresybano.gresybano.common.extensions.loadUrl
import es.gresybano.gresybano.domain.entities.PublicationBo
import es.gresybano.gresybano.feature.application.databinding.RowHeightPublicationBinding

class HeightPublicationAdapter(private val listenerClickElement: (publication: PublicationBo) -> Unit) :
    ListHeaderSeeMoreAdapter<PublicationBo>(diffUtils) {

    companion object {
        private const val IMAGE_SIZE = 150
    }

    class ViewHolderCategory(private val viewBinding: RowHeightPublicationBinding) :
        RecyclerView.ViewHolder(viewBinding.root) {

        fun bind(
            publication: PublicationBo,
            listenerClickElement: (publication: PublicationBo) -> Unit
        ) {
            with(viewBinding) {
                setOnClickParent { listenerClickElement(publication) }
                setData(publication)
            }
        }

        private fun RowHeightPublicationBinding.setData(publication: PublicationBo) {
            rowHeightPublicationImgImagePublication.loadUrl(
                publication.listImages.first(),
                IMAGE_SIZE,
                IMAGE_SIZE
            )
            rowHeightPublicationLabelNamePublication.text = publication.category
            rowHeightPublicationLabelAmountPublication.text = publication.listImages.size.toString()
        }

        private fun RowHeightPublicationBinding.setOnClickParent(action: () -> Unit) {
            root.setOnClickListener { action() }
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        (holder as? ViewHolderCategory)?.bind(currentList[position], listenerClickElement)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolderCategory {
        return ViewHolderCategory(
            RowHeightPublicationBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }
}

private val diffUtils = object : DiffUtil.ItemCallback<PublicationBo>() {
    override fun areItemsTheSame(
        oldItem: PublicationBo,
        newItem: PublicationBo
    ) = oldItem.id == newItem.id

    override fun areContentsTheSame(
        oldItem: PublicationBo,
        newItem: PublicationBo
    ) = oldItem.id == newItem.id
            && oldItem.publishDate == newItem.publishDate
            && oldItem.listImages == newItem.listImages
}