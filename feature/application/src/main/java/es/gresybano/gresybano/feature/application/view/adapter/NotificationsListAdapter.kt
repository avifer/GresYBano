package es.gresybano.gresybano.feature.application.view.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import es.avifer.listheaderseemore.ListHeaderSeeMoreAdapter
import es.gresybano.gresybano.common.extensions.*
import es.gresybano.gresybano.domain.notification.entity.MessageNotificationBo
import es.gresybano.gresybano.feature.application.databinding.RowNotificationBinding
import es.gresybano.gresybano.feature.application.databinding.RowSeparatorDateBinding
import java.util.*

class NotificationsListAdapter(
    private val listenerClickElement: (notification: MessageNotificationBo) -> Unit,
) : ListHeaderSeeMoreAdapter<NotificationsListAdapter.MessageNotificationVo>(diffUtils) {

    companion object {
        private const val TYPE_NEW_PUBLICATION = 0
        private const val TYPE_SEPARATOR_DATE = 1
        private const val TYPE_ERROR = 2
    }

    sealed class MessageNotificationVo(
        open val dateReceived: Date,
    ) {

        data class NewPublicationVo(
            val idNotification: Long,
            val title: String,
            val subtitle: String,
            var isOpened: Boolean,
            val id: Long,
            val mainImage: String,
            override val dateReceived: Date,
        ) : MessageNotificationVo(dateReceived)

        data class SeparatorDateVo(
            val date: Calendar,
        ) : MessageNotificationVo(date.time)

        object Error : MessageNotificationVo(Date())

        fun isEquals(message: MessageNotificationVo): Boolean {
            return if (this.javaClass == message.javaClass) {
                when (this) {
                    is NewPublicationVo -> {
                        if (message is NewPublicationVo) {
                            idNotification == message.idNotification &&
                                    title == message.title &&
                                    subtitle == message.subtitle &&
                                    isOpened == message.isOpened &&
                                    id == message.id &&
                                    mainImage == message.mainImage &&
                                    dateReceived == message.dateReceived

                        } else {
                            false
                        }
                    }
                    else -> {
                        dateReceived == message.dateReceived
                    }
                }

            } else {
                false
            }
        }
    }

    override fun getItemViewType(position: Int): Int {
        return when (currentList[position]) {
            is MessageNotificationVo.NewPublicationVo -> TYPE_NEW_PUBLICATION
            is MessageNotificationVo.SeparatorDateVo -> TYPE_SEPARATOR_DATE
            MessageNotificationVo.Error -> TYPE_ERROR
        }
    }

    inner class ViewHolderNewPublication(
        private val viewBinding: RowNotificationBinding,
        listenerClickElement: (notification: MessageNotificationBo) -> Unit,
    ) : RecyclerView.ViewHolder(viewBinding.root) {

        init {
            viewBinding.rowNotificationCard.setOnClickListener {
                listenerClickElement(currentList[adapterPosition].toBo())
            }
        }

        fun bind(publication: MessageNotificationVo.NewPublicationVo) {
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

    inner class ViewHolderSeparatorDate(
        private val viewBinding: RowSeparatorDateBinding,
    ) : RecyclerView.ViewHolder(viewBinding.root) {

        fun bind(publication: MessageNotificationVo.SeparatorDateVo) {
            viewBinding.rowSeparatorDateLabelDate.text =
                publication.dateReceived.getToString(viewBinding.root.context)
        }

    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (currentList[position]) {
            is MessageNotificationVo.NewPublicationVo -> {
                (holder as? ViewHolderNewPublication)?.bind(
                    currentList[position] as MessageNotificationVo.NewPublicationVo
                )
            }
            is MessageNotificationVo.SeparatorDateVo -> {
                (holder as? ViewHolderSeparatorDate)?.bind(
                    currentList[position] as MessageNotificationVo.SeparatorDateVo
                )
            }
            MessageNotificationVo.Error -> {}
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return when (viewType) {
            TYPE_NEW_PUBLICATION -> ViewHolderNewPublication(
                RowNotificationBinding.inflate(
                    LayoutInflater.from(parent.context),
                    parent,
                    false
                ),
                listenerClickElement
            )
            TYPE_SEPARATOR_DATE -> ViewHolderSeparatorDate(
                RowSeparatorDateBinding.inflate(
                    LayoutInflater.from(parent.context),
                    parent,
                    false
                )
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

    fun addElements(list: List<MessageNotificationBo>) {
        submitList(
            mutableListOf<MessageNotificationVo>().apply {
                addAll(list.map { it.toVo() })
                addAll(getDateSeparator(this))
                sortByDescending { it.dateReceived }
            }
        )
    }

    private fun getDateSeparator(listElements: List<MessageNotificationVo>): List<MessageNotificationVo> {
        val listDate = listElements.map { it.dateReceived }.toSet().toList()
        return listDate.map {
            MessageNotificationVo.SeparatorDateVo(
                Calendar.getInstance().putDate(it).putInLastHour()
            )
        }.distinct()
    }

    private fun MessageNotificationBo.toVo() =
        when (this) {
            is MessageNotificationBo.NewPublicationBo -> MessageNotificationVo.NewPublicationVo(
                idNotification = this.idNotification,
                title = this.title,
                subtitle = this.subtitle,
                isOpened = this.isOpened,
                dateReceived = this.dateReceived,
                id = this.id,
                mainImage = this.mainImage
            )
            else -> MessageNotificationVo.Error
        }

    private fun MessageNotificationVo.toBo() =
        when (this) {
            is MessageNotificationVo.NewPublicationVo -> MessageNotificationBo.NewPublicationBo(
                idNotification = this.idNotification,
                title = this.title,
                subtitle = this.subtitle,
                isOpened = this.isOpened,
                dateReceived = this.dateReceived,
                id = this.id,
                mainImage = this.mainImage
            )
            else -> MessageNotificationBo.Error
        }

}

private val diffUtils =
    object : DiffUtil.ItemCallback<NotificationsListAdapter.MessageNotificationVo>() {
        override fun areItemsTheSame(
            oldItem: NotificationsListAdapter.MessageNotificationVo,
            newItem: NotificationsListAdapter.MessageNotificationVo
        ) = oldItem.isEquals(newItem)

        override fun areContentsTheSame(
            oldItem: NotificationsListAdapter.MessageNotificationVo,
            newItem: NotificationsListAdapter.MessageNotificationVo
        ) = oldItem.isEquals(newItem)

    }