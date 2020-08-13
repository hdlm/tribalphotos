package com.tribal.tribalphotos.ui.adapter.itemModel

import com.tribal.tribalphotos.ui.adapter.typeFactory.BaseTypesFactory

abstract class ItemModel {
    abstract fun type(typesFactory: BaseTypesFactory): Int
}