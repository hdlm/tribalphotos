package com.tribal.tribalphotos.ui.di.modules

import androidx.fragment.app.Fragment
import com.tribal.tribalphotos.repository.TribalPhotosRepository
import com.tribal.tribalphotos.repository.TribalPhotosRepositoryImpl
import com.tribal.tribalphotos.ui.TribalPhotosViewModel
import com.tribal.tribalphotos.ui.photo.PhotoGalleryFragment
import org.koin.dsl.module
import org.koin.androidx.viewmodel.dsl.viewModel

val viewModelModule = module {
    viewModel { TribalPhotosViewModel(get()) }
}

val repoModule = module {
    single<TribalPhotosRepository> { TribalPhotosRepositoryImpl(get()) }
}