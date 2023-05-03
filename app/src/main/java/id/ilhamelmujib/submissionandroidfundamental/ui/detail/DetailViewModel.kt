package id.ilhamelmujib.submissionandroidfundamental.ui.detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import id.ilhamelmujib.submissionandroidfundamental.data.UserRepository
import id.ilhamelmujib.submissionandroidfundamental.data.model.DetailUserResponse
import kotlinx.coroutines.launch

class DetailViewModel(
    private val userRepository: UserRepository
) : ViewModel() {


    fun detailUser(username: String)= userRepository.getDetailUser(username)

}