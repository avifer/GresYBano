package es.gresybano.gresybano.feature.application.view.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import es.avifer.listheaderseemore.ListHeaderSeeMoreAdapter
import es.gresybano.gresybano.common.extensions.hide
import es.gresybano.gresybano.common.extensions.loadUrl
import es.gresybano.gresybano.common.extensions.visible
import es.gresybano.gresybano.domain.entities.PublicationBo
import es.gresybano.gresybano.feature.application.databinding.RowHeightPublicationBinding

class HeightPublicationAdapter(
    private val listenerClickElement: (publication: PublicationBo) -> Unit,
    private val favoriteEnable: Boolean = false,
    private val centerGravity: Boolean = false,
) :
    ListHeaderSeeMoreAdapter<PublicationBo>(diffUtils) {

    companion object {
        private const val IMAGE_SIZE = 150
    }

    class ViewHolderCategory(private val viewBinding: RowHeightPublicationBinding) :
        RecyclerView.ViewHolder(viewBinding.root) {

        fun bind(
            publication: PublicationBo,
            listenerClickElement: (publication: PublicationBo) -> Unit,
            favoriteEnable: Boolean,
            centerGravity: Boolean
        ) {
            with(viewBinding) {
                setOnClickParent { listenerClickElement(publication) }
                if (centerGravity) {
                    setGravityCenter()
                }
                setData(publication, favoriteEnable)
            }
        }

        private fun RowHeightPublicationBinding.setData(
            publication: PublicationBo,
            favoriteEnable: Boolean
        ) {
            rowHeightPublicationImgImagePublication.loadUrl(
                publication.listImages.first(),
                IMAGE_SIZE,
                IMAGE_SIZE
            )
            //rowHeightPublicationLabelNamePublication.text = publication.category  //TODO Ver como implementar
            rowHeightPublicationLabelAmountPublication.text = publication.listImages.size.toString()
            if (favoriteEnable) {
                rowHeightPublicationImgFavorite.visible(publication.favorite)

            } else {
                rowHeightPublicationImgFavorite.hide()
            }
        }

        private fun RowHeightPublicationBinding.setOnClickParent(action: () -> Unit) {
            root.setOnClickListener { action() }
        }

        private fun RowHeightPublicationBinding.setGravityCenter() {
            root.layoutParams.width = ViewGroup.LayoutParams.MATCH_PARENT
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        (holder as? ViewHolderCategory)?.bind(
            currentList[position],
            listenerClickElement,
            favoriteEnable,
            centerGravity
        )
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