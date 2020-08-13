package com.tribal.tribalphotos.ui.di

import com.tribal.tribalphotos.ui.di.modules.repoModule
import com.tribal.tribalphotos.ui.di.modules.viewModelModule
import org.koin.core.context.loadKoinModules


object TribalPhotosModule {
    private val modules = listOf(viewModelModule, repoModule)
    fun init() = loadKoinModules( modules )
}