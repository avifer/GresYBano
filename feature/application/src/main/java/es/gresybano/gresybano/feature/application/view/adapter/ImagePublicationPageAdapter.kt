package es.gresybano.gresybano.feature.application.view.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import es.gresybano.gresybano.common.extensions.loadUrl
import es.gresybano.gresybano.feature.application.databinding.ItemImagePublicationSliderBinding

class ImagePublicationPageAdapter(listImages: List<String>) :
    ListAdapter<String, ImagePublicationPageAdapter.ViewHolder>(diffUtils) {

    init {
        submitList(listImages)
    }

    inner class ViewHolder(private val binding: ItemImagePublicationSliderBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(urlImage: String) {
            binding.itemImagePublicationSliderImgImage.loadUrl(urlImage)
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            ItemImagePublicationSliderBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(currentList[position])
    }

}

private val diffUtils = object : DiffUtil.ItemCallback<String>() {
    override fun areItemsTheSame(
        oldItem: String,
        newItem: String
    ) = oldItem == newItem

    override fun areContentsTheSame(
        oldItem: String,
        newItem: String
    ) = oldItem == newItem
}