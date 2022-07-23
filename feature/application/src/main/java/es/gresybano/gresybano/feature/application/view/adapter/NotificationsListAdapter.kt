package es.gresybano.gresybano.feature.application.view.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import es.avifer.listheaderseemore.ListHeaderSeeMoreAdapter
import es.gresybano.gresybano.common.extensions.getColorState
import es.gresybano.gresybano.common.extensions.loadUrl
import es.gresybano.gresybano.domain.notification.entity.MessageNotificationBo
import es.gresybano.gresybano.feature.application.databinding.RowNotificationBinding

class NotificationsListAdapter(
    private val listenerClickElement: (notification: MessageNotificationBo) -> Unit,
) : ListHeaderSeeMoreAdapter<MessageNotificationBo>(diffUtils) {

    companion object {
        private const val TYPE_NEW_PUBLICATION = 0
        private const val TYPE_ERROR = 1
    }

    override fun getItemViewType(position: Int): Int {
        return when (currentList[position]) {
            is MessageNotificationBo.NewPublicationBo -> TYPE_NEW_PUBLICATION
            MessageNotificationBo.Error -> TYPE_ERROR
        }
    }

    inner class ViewHolderNewPublication(
        private val viewBinding: RowNotificationBinding,
        listenerClickElement: (notification: MessageNotificationBo) -> Unit,
    ) : RecyclerView.ViewHolder(viewBinding.root) {

        init {
            viewBinding.rowNotificationCard.setOnClickListener {
                listenerClickElement(currentList[adapterPosition])
            }
        }

        fun bind(publication: MessageNotificationBo.NewPublicationBo) {
            with(viewBinding) {
                rowNotificationLabelTitle.text = publication.title
                rowNotificationLabelSubtitle.text = publication.subtitle
                rowNotificationImgNotification.loadUrl(publication.mainImage)
                rowNotificationCard.backgroundTintList =
                    root.context.getColorState(
                        when (publication.isOpened) {
                            true -> es.gresybano.gresybano.common.R.color.white
                            false -> es.gresybano.gresybano.common.R.color.secondary_color
                        }
                    )
            }
        }

    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (currentList[position]) {
            is MessageNotificationBo.NewPublicationBo -> {
                (holder as? ViewHolderNewPublication)?.bind(
                    currentList[position] as MessageNotificationBo.NewPublicationBo
                )
            }
            MessageNotificationBo.Error -> {}
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolderNewPublication {
        return when (viewType) {
            TYPE_NEW_PUBLICATION -> ViewHolderNewPublication(
                RowNotificationBinding.inflate(
                    LayoutInflater.from(parent.context),
                    parent,
                    false
                ),
                listenerClickElement
            )
            else -> ViewHolderNewPublication(
                RowNotificationBinding.inflate(
                    LayoutInflater.from(parent.context),
                    parent,
                    false
                ),
                listenerClickElement
            )
        }
    }
}

private val diffUtils = object : DiffUtil.ItemCallback<MessageNotificationBo>() {
    override fun areItemsTheSame(
        oldItem: MessageNotificationBo,
        newItem: MessageNotificationBo
    ) = oldItem.idNotification == newItem.idNotification

    override fun areContentsTheSame(
        oldItem: MessageNotificationBo,
        newItem: MessageNotificationBo
    ) = oldItem.idNotification == newItem.idNotification
            && oldItem.title == newItem.title
            && oldItem.subtitle == newItem.subtitle
}