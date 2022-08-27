package es.gresybano.gresybano.feature.application.view.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.annotation.IdRes
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import es.gresybano.gresybano.common.extensions.visible
import es.gresybano.gresybano.feature.application.databinding.RowElementHelpBinding
import es.gresybano.gresybano.feature.application.databinding.RowSeparatorHelpBinding


class ElementHelpAdapter(
    private val listenerClickElement: (notification: ElementHelpVo) -> Unit,
) : ListAdapter<ElementHelpAdapter.ElementHelpVo, RecyclerView.ViewHolder>(diffUtils) {

    companion object {
        private const val TYPE_HELP_ELEMENT = 0
        private const val TYPE_SEPARATOR = 1
    }

    enum class IdElementHelp {
        CALL_ME, FIND_ME
    }

    sealed class ElementHelpVo {

        data class ElementVo(
            val id: IdElementHelp,
            @IdRes val icon: Int? = null,
            val name: String,
            val description: String? = null
        ) : ElementHelpVo()


        object SeparatorVo : ElementHelpVo()

        fun isEquals(message: ElementHelpVo): Boolean {
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
            is ElementHelpVo.ElementVo -> TYPE_HELP_ELEMENT
            ElementHelpVo.SeparatorVo -> TYPE_SEPARATOR
        }
    }

    inner class ViewHolderElement(
        private val viewBinding: RowElementHelpBinding,
    ) : RecyclerView.ViewHolder(viewBinding.root) {

        init {
            viewBinding.rowElementHelpCard.setOnClickListener {
                listenerClickElement(currentList[adapterPosition])
            }
        }

        fun bind(element: ElementHelpVo.ElementVo) {
            with(element) {
                icon?.let {
                    viewBinding.rowElementHelpImgElement.setBackgroundResource(it)
                }
                viewBinding.rowElementHelpLabelTitle.text = name
                viewBinding.rowElementHelpLabelSubtitle.text = description
                viewBinding.rowElementHelpLabelSubtitle.visible(description != null)
            }
        }

    }

    inner class ViewHolderSeparator(
        viewBinding: RowSeparatorHelpBinding,
    ) : RecyclerView.ViewHolder(viewBinding.root) {

        fun bind() {}

    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (currentList[position]) {
            is ElementHelpVo.ElementVo -> {
                (holder as? ViewHolderElement)?.bind(
                    currentList[position] as ElementHelpVo.ElementVo
                )
            }
            is ElementHelpVo.SeparatorVo -> {
                (holder as? ViewHolderSeparator)?.bind()
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return when (viewType) {
            TYPE_HELP_ELEMENT -> ViewHolderElement(
                RowElementHelpBinding.inflate(
                    LayoutInflater.from(parent.context),
                    parent,
                    false
                )
            )
            else -> ViewHolderSeparator(
                RowSeparatorHelpBinding.inflate(
                    LayoutInflater.from(parent.context),
                    parent,
                    false
                )
            )
        }
    }

}

private val diffUtils =
    object : DiffUtil.ItemCallback<ElementHelpAdapter.ElementHelpVo>() {
        override fun areItemsTheSame(
            oldItem: ElementHelpAdapter.ElementHelpVo,
            newItem: ElementHelpAdapter.ElementHelpVo
        ) = oldItem.isEquals(newItem)

        override fun areContentsTheSame(
            oldItem: ElementHelpAdapter.ElementHelpVo,
            newItem: ElementHelpAdapter.ElementHelpVo
        ) = oldItem.isEquals(newItem)

    }