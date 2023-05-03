package id.ilhamelmujib.submissionandroidfundamental.ui.detail.follow

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import id.ilhamelmujib.submissionandroidfundamental.data.UserRepository
import id.ilhamelmujib.submissionandroidfundamental.di.Injection

class FollowViewModelFactory private constructor(
    private val userRepository: UserRepository
): ViewModelProvider.NewInstanceFactory(){

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(FollowViewModel::class.java)){
            return  FollowViewModel(userRepository) as T
        }

        throw IllegalArgumentException("Unknown ViewModel class: " + modelClass.name)
    }

    companion object{
        @Volatile
        private var instance: FollowViewModelFactory? = null

        fun getInstance(context: Context ): FollowViewModelFactory = instance ?: synchronized(this) {
            instance ?: FollowViewModelFactory(Injection.provideRepository(context))
        }.also { instance = it }
    }

}