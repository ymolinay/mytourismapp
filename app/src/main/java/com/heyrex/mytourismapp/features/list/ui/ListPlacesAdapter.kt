package com.heyrex.mytourismapp.features.list.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.heyrex.mytourismapp.core.extensions.loadImage
import com.heyrex.mytourismapp.databinding.HeaderPlaceTourismViewBinding
import com.heyrex.mytourismapp.databinding.ItemPlaceTourismViewBinding
import com.heyrex.mytourismapp.features.list.domain.model.PlaceSimple

class ListPlacesAdapter(
    var list: MutableList<PlaceSimple?>,
    var onClick: (PlaceSimple) -> Unit = {}
) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    companion object {
        private const val ITEM_HEADER = 1
        private const val ITEM_CARD = 2
    }

    init {
        list.add(0, null)
    }

    override fun getItemViewType(position: Int): Int {
        return when {
            list[position] == null -> ITEM_HEADER
            else -> ITEM_CARD
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return when (viewType) {
            ITEM_CARD -> ItemPLaceViewHolder(
                ItemPlaceTourismViewBinding.inflate(
                    LayoutInflater.from(parent.context), parent, false
                )
            )

            else -> HeaderPLaceViewHolder(
                HeaderPlaceTourismViewBinding.inflate(
                    LayoutInflater.from(parent.context),
                    parent,
                    false
                )
            )
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (holder) {
            is ItemPLaceViewHolder -> {
                holder.bind(list[position], onClick)
            }

            is HeaderPLaceViewHolder -> {
                holder.bind()
            }
        }
    }

    override fun getItemCount() = list.size
}

class ItemPLaceViewHolder(val view: ItemPlaceTourismViewBinding) :
    RecyclerView.ViewHolder(view.root) {

    fun bind(item: PlaceSimple?, onClick: (PlaceSimple) -> Unit) {
        item?.let { item ->
            view.buttonDetail.setOnClickListener { onClick.invoke(item) }
            view.title.text = item.title
            view.description.text = item.description
            view.image.loadImage(item.image)
        }
    }
}

class HeaderPLaceViewHolder(val view: HeaderPlaceTourismViewBinding) :
    RecyclerView.ViewHolder(view.root) {

    fun bind() {}
}