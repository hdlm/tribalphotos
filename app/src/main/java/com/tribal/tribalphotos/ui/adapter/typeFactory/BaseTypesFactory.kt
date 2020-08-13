package com.tribal.tribalphotos.ui.adapter.typeFactory

import android.view.View
import com.tribal.tribalphotos.ui.adapter.holder.DynamicAdapterViewHolder

interface BaseTypesFactory {
    fun holder(type: Int, view: View): DynamicAdapterViewHolder<*>
}