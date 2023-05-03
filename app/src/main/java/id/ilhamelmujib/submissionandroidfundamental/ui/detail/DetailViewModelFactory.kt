package id.ilhamelmujib.submissionandroidfundamental.ui.detail

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import id.ilhamelmujib.submissionandroidfundamental.data.UserRepository
import id.ilhamelmujib.submissionandroidfundamental.di.Injection

class DetailViewModelFactory private constructor(
    private val userRepository: UserRepository
): ViewModelProvider.NewInstanceFactory(){

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(DetailViewModel::class.java)){
            return  DetailViewModel(userRepository) as T
        }

        throw IllegalArgumentException("Unknown ViewModel class: " + modelClass.name)
    }

    companion object{
        @Volatile
        private var instance: DetailViewModelFactory? = null

        fun getInstance(context: Context ): DetailViewModelFactory = instance ?: synchronized(this) {
            instance ?: DetailViewModelFactory(Injection.provideRepository(context))
        }.also { instance = it }
    }

}