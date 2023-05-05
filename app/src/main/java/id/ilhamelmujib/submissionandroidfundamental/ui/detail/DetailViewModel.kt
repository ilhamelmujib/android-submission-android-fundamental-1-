package id.ilhamelmujib.submissionandroidfundamental.ui.detail

import androidx.lifecycle.ViewModel
import id.ilhamelmujib.submissionandroidfundamental.data.UserRepository

class DetailViewModel(
    private val userRepository: UserRepository
) : ViewModel() {


    fun detailUser(username: String)= userRepository.getDetailUser(username)

}