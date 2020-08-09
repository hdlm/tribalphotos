package com.tribal.tribalphotos.ui.di

import com.tribal.tribalphotos.ui.TribalPhotosViewModel
import org.koin.dsl.module
import org.koin.androidx.viewmodel.dsl.viewModel

val viewModelModule = module {
    viewModel {
        TribalPhotosViewModel(get())
    }
}