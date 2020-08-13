package com.tribal.tribalphotos.ui.adapter.holder

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.tribal.tribalphotos.ui.adapter.itemModel.ItemModel

abstract class DynamicAdapterViewHolder<in T>(view: View) : RecyclerView.ViewHolder(view) {
    abstract fun bind(item: T, position: Int, onClick: (ItemModel) -> Unit)
}