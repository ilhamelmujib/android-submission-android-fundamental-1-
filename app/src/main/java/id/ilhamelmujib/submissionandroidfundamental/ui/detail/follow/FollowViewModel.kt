package id.ilhamelmujib.submissionandroidfundamental.ui.detail.follow

import androidx.lifecycle.ViewModel
import id.ilhamelmujib.submissionandroidfundamental.data.UserRepository

class FollowViewModel(
    private val userRepository: UserRepository
) : ViewModel() {

    fun followUser(username: String, category: String) =
        userRepository.getFollow(username, category)

}