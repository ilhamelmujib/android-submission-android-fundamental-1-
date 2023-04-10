package id.ilhamelmujib.submissionandroidfundamental.ui.detail.follow

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import id.ilhamelmujib.submissionandroidfundamental.model.Result
import id.ilhamelmujib.submissionandroidfundamental.model.User
import id.ilhamelmujib.submissionandroidfundamental.service.ApiConfig
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class FollowViewModel : ViewModel() {
    private val _user = MutableLiveData<List<User>>()
    val user: LiveData<List<User>> = _user

    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean> = _isLoading

    fun followUser(username: String, category: String) {
        _isLoading.postValue(true)
        val client = ApiConfig.getApiService().followUser(username, category)
        client.enqueue(object : Callback<List<User>> {
            override fun onResponse(call: Call<List<User>>, response: Response<List<User>>) {
                response.body()?.let {
                    _user.postValue(it)
                }
                _isLoading.postValue(false)
            }

            override fun onFailure(call: Call<List<User>>, t: Throwable) {
                _isLoading.postValue(false)
            }
        })
    }
}