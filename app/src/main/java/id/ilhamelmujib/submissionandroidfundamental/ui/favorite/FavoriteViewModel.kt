package id.ilhamelmujib.submissionandroidfundamental.ui.favorite

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import id.ilhamelmujib.submissionandroidfundamental.data.UserRepository
import kotlinx.coroutines.launch

class FavoriteViewModel(private val userRepository: UserRepository) : ViewModel() {

    fun getFavorite() = userRepository.getFavorites()

    fun deleteFavorite(id: Long) {
        viewModelScope.launch {
            userRepository.deleteFavorite(id)
        }
    }

}