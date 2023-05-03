package id.ilhamelmujib.submissionandroidfundamental.ui.user

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import id.ilhamelmujib.submissionandroidfundamental.data.UserRepository
import id.ilhamelmujib.submissionandroidfundamental.di.Injection

class UserViewModelFactory private constructor(
    private val userRepository: UserRepository
): ViewModelProvider.NewInstanceFactory(){

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(UserViewModel::class.java)){
            return  UserViewModel(userRepository) as T
        }

        throw IllegalArgumentException("Unknown ViewModel class: " + modelClass.name)
    }

    companion object{
        @Volatile
        private var instance: UserViewModelFactory? = null

        fun getInstance(context: Context ): UserViewModelFactory = instance ?: synchronized(this) {
            instance ?: UserViewModelFactory(Injection.provideRepository(context))
        }.also { instance = it }
    }

}