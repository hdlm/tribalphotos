package com.tribal.tribalphotos.ui.di.modules

import com.tribal.tribalphotos.repository.*
import com.tribal.tribalphotos.ui.favorite.PhotoFavoriteViewModel
import com.tribal.tribalphotos.ui.photo.PhotoGalleryViewModel
import org.koin.dsl.module
import org.koin.androidx.viewmodel.dsl.viewModel

val viewModelModule = module {
    viewModel { PhotoGalleryViewModel(get()) }
    viewModel { PhotoFavoriteViewModel() }
}

val repoModule = module {
    single<PhotoRepository> { PhotoRepositoryImpl(get()) }
}