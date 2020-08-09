package com.tribal.tribalphotos.ui.di

import org.koin.core.context.loadKoinModules


object TribalPhotosModule {
    fun init() = loadKoinModules(
        viewModelModule
    )
}