package com.heyrex.mytourismapp.features.detail.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.heyrex.mytourismapp.core.extensions.loadImage
import com.heyrex.mytourismapp.databinding.ItemDetailAlbumViewBinding
import com.heyrex.mytourismapp.features.detail.domain.model.PlaceDetailAlbum

class DetailAlbumAdapter(private val imageUrls: List<PlaceDetailAlbum>) :
    RecyclerView.Adapter<ImageViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ImageViewHolder {
        return ImageViewHolder(
            ItemDetailAlbumViewBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: ImageViewHolder, position: Int) {
        holder.bind(imageUrls[position])
    }

    override fun getItemCount(): Int = imageUrls.size
}

class ImageViewHolder(val view: ItemDetailAlbumViewBinding) : RecyclerView.ViewHolder(view.root) {

    fun bind(item: PlaceDetailAlbum) {
        view.image.loadImage(item.image)
    }
}