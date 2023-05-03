package id.ilhamelmujib.submissionandroidfundamental.ui.user

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import id.ilhamelmujib.submissionandroidfundamental.data.UserRepository
import id.ilhamelmujib.submissionandroidfundamental.data.local.entity.UserEntity
import kotlinx.coroutines.launch

class UserViewModel(private val userRepository: UserRepository) : ViewModel() {
    fun searchUser(username: String) = userRepository.searchUser(username)

    fun setFavorite(user: UserEntity){
        viewModelScope.launch {
            userRepository.setFavorite(user)
        }
    }

}