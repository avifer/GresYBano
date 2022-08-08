package es.gresybano.gresybano.feature.application.view.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.annotation.IdRes
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import es.gresybano.gresybano.feature.application.databinding.RowElementSettingsBinding
import es.gresybano.gresybano.feature.application.databinding.RowSeparatorSettingsBinding

class ElementSettingsAdapter(
    private val listenerClickElement: (notification: ElementSettingVo) -> Unit,
) : ListAdapter<ElementSettingsAdapter.ElementSettingVo, RecyclerView.ViewHolder>(diffUtils) {

    companion object {
        private const val TYPE_SETTING_ELEMENT = 0
        private const val TYPE_SEPARATOR = 1
    }

    enum class IdElementSetting {
        NOTIFICATIONS, RANK_US, SHARE_APP, HELP, VERSION
    }

    sealed class ElementSettingVo {

        data class ElementVo(
            val id: IdElementSetting,
            @IdRes val icon: Int? = null,
            val name: String,
            val description: String
        ) : ElementSettingVo()


        object SeparatorVo : ElementSettingVo()

        fun isEquals(message: ElementSettingVo): Boolean {
            return if (this.javaClass == message.javaClass) {
                when (this) {
                    is ElementVo -> {
                        if (message is ElementVo) {
                            icon == message.icon &&
                                    name == message.name &&
                                    description == message.description

                        } else {
                            false
                        }
                    }
                    else -> {
                        false
                    }
                }

            } else {
                false
            }
        }
    }

    override fun getItemViewType(position: Int): Int {
        return when (currentList[position]) {
            is ElementSettingVo.ElementVo -> TYPE_SETTING_ELEMENT
            ElementSettingVo.SeparatorVo -> TYPE_SEPARATOR
        }
    }

    inner class ViewHolderElement(
        private val viewBinding: RowElementSettingsBinding,
    ) : RecyclerView.ViewHolder(viewBinding.root) {

        init {
            viewBinding.rowElementSettingsCard.setOnClickListener {
                listenerClickElement(currentList[adapterPosition])
            }
        }

        fun bind(element: ElementSettingVo.ElementVo) {
            with(element) {
                icon?.let {
                    viewBinding.rowElementSettingsImgNotification.setBackgroundResource(it)
                }
                viewBinding.rowElementSettingsLabelTitle.text = name
                viewBinding.rowElementSettingsLabelSubtitle.text = description
            }
        }

    }

    inner class ViewHolderSeparator(
        private val viewBinding: RowSeparatorSettingsBinding,
    ) : RecyclerView.ViewHolder(viewBinding.root) {

        fun bind() {}

    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (currentList[position]) {
            is ElementSettingVo.ElementVo -> {
                (holder as? ViewHolderElement)?.bind(
                    currentList[position] as ElementSettingVo.ElementVo
                )
            }
            is ElementSettingVo.SeparatorVo -> {
                (holder as? ViewHolderSeparator)?.bind()
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return when (viewType) {
            TYPE_SETTING_ELEMENT -> ViewHolderElement(
                RowElementSettingsBinding.inflate(
                    LayoutInflater.from(parent.context),
                    parent,
                    false
                )
            )
            else -> ViewHolderSeparator(
                RowSeparatorSettingsBinding.inflate(
                    LayoutInflater.from(parent.context),
                    parent,
                    false
                )
            )
        }
    }

}

private val diffUtils =
    object : DiffUtil.ItemCallback<ElementSettingsAdapter.ElementSettingVo>() {
        override fun areItemsTheSame(
            oldItem: ElementSettingsAdapter.ElementSettingVo,
            newItem: ElementSettingsAdapter.ElementSettingVo
        ) = oldItem.isEquals(newItem)

        override fun areContentsTheSame(
            oldItem: ElementSettingsAdapter.ElementSettingVo,
            newItem: ElementSettingsAdapter.ElementSettingVo
        ) = oldItem.isEquals(newItem)

    }