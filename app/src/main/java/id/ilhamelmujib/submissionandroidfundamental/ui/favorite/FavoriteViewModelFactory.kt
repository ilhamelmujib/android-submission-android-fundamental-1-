package id.ilhamelmujib.submissionandroidfundamental.ui.favorite

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import id.ilhamelmujib.submissionandroidfundamental.data.UserRepository
import id.ilhamelmujib.submissionandroidfundamental.di.Injection

class FavoriteViewModelFactory private constructor(
    private val userRepository: UserRepository
): ViewModelProvider.NewInstanceFactory(){

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(FavoriteViewModel::class.java)){
            return  FavoriteViewModel(userRepository) as T
        }

        throw IllegalArgumentException("Unknown ViewModel class: " + modelClass.name)
    }

    companion object{
        @Volatile
        private var instance: FavoriteViewModelFactory? = null

        fun getInstance(context: Context ): FavoriteViewModelFactory = instance ?: synchronized(this) {
            instance ?: FavoriteViewModelFactory(Injection.provideRepository(context))
        }.also { instance = it }
    }

}