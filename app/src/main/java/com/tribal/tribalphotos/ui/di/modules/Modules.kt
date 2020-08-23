package com.tribal.tribalphotos.ui.di.modules

import com.tribal.tribalphotos.repository.*
import com.tribal.tribalphotos.ui.favorite.PhotoFavoritesViewModel
import com.tribal.tribalphotos.ui.photo.PhotoGalleryViewModel
import org.koin.dsl.module
import org.koin.androidx.viewmodel.dsl.viewModel

val viewModelModule = module {
    viewModel { PhotoGalleryViewModel(get(),get()) }
    viewModel { PhotoFavoritesViewModel(get()) }
}

val repoModule = module {
    single<PhotosRepository> { PhotosRepositoryImpl(get()) }
    single<FavoritesRepository> { FavoriteRepositoryImpl(get()) }
}