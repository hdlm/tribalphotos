package com.tribal.tribalphotos.ui.adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.tribal.tribalphotos.MainActivity
import com.tribal.tribalphotos.ui.adapter.holder.DynamicAdapterViewHolder
import com.tribal.tribalphotos.ui.adapter.itemModel.ItemModel
import com.tribal.tribalphotos.ui.adapter.typeFactory.BaseTypesFactory

class DynamicAdapter(
    private val typeFactory: BaseTypesFactory,
    var items: List<ItemModel>,
    private val onClick: (ItemModel) -> Unit = { }
) : RecyclerView.Adapter<DynamicAdapterViewHolder<ItemModel>>() {

    override fun getItemCount() = items.count()

    override fun onCreateViewHolder(
        parent: ViewGroup,
        p1: Int
    ): DynamicAdapterViewHolder<ItemModel> {
        val view = LayoutInflater.from(parent.context).inflate(p1, parent, false)
        return typeFactory.holder(p1, view) as DynamicAdapterViewHolder<ItemModel>
    }

    override fun getItemViewType(position: Int): Int {
        return items[position].type(typeFactory)
    }

    override fun onBindViewHolder(holder: DynamicAdapterViewHolder<ItemModel>, position: Int) {
        holder.bind(items[position], position, onClick)
    }
}