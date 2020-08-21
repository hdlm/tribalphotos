package com.tribal.tribalphotos.ui.di.modules

import com.tribal.tribalphotos.repository.PhotosRepository
import com.tribal.tribalphotos.repository.PhotosRepositoryImpl
import com.tribal.tribalphotos.ui.photo.PhotoGalleryViewModel
import org.koin.dsl.module
import org.koin.androidx.viewmodel.dsl.viewModel

val viewModelModule = module {
    viewModel { PhotoGalleryViewModel(get()) }
}

val repoModule = module {
    single<PhotosRepository> { PhotosRepositoryImpl(get()) }
}