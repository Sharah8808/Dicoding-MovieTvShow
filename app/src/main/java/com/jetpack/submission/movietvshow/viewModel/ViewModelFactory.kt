package com.jetpack.submission.movietvshow.viewModel

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.jetpack.submission.movietvshow.repository.EntityRepository
import com.jetpack.submission.movietvshow.utils.Injection

class ViewModelFactory private constructor(private val entityRepository: EntityRepository) : ViewModelProvider.NewInstanceFactory(){

    companion object{
        @Volatile
        private var instance : ViewModelFactory? = null

        fun getInstance(context: Context) : ViewModelFactory =
            instance ?: synchronized(this) {
                instance ?: ViewModelFactory(Injection.provideRepository(context))
                    .apply { instance = this }
            }
    }

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        when{
            modelClass.isAssignableFrom(ListViewModel::class.java) -> {
                return ListViewModel(entityRepository) as T
            }
            modelClass.isAssignableFrom(DetailViewModel::class.java) -> {
                return DetailViewModel(entityRepository) as T
            }
            modelClass.isAssignableFrom(FavoriteMovieVM::class.java) -> {
                return FavoriteMovieVM(entityRepository) as T
            }
            modelClass.isAssignableFrom(FavoriteTvShowVM::class.java) -> {
                return FavoriteTvShowVM(entityRepository) as T
            }
            else -> { throw Throwable("Unknown ViewModel class" + modelClass) }
        }
    }

}