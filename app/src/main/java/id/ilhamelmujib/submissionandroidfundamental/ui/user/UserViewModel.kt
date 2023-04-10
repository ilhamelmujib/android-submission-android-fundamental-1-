package id.ilhamelmujib.submissionandroidfundamental.ui.user

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import id.ilhamelmujib.submissionandroidfundamental.model.User
import id.ilhamelmujib.submissionandroidfundamental.service.ApiConfig
import id.ilhamelmujib.submissionandroidfundamental.model.Result
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class UserViewModel : ViewModel() {
    private val _user = MutableLiveData<List<User>>()
    val user: LiveData<List<User>> = _user

    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean> = _isLoading

    fun searchUser(username: String) {
        _isLoading.postValue(true)
        val client = ApiConfig.getApiService().searchUser(username)
        client.enqueue(object : Callback<Result> {
            override fun onResponse(call: Call<Result>, response: Response<Result>) {
                response.body()?.items?.let {
                    _user.postValue(it)
                }
                _isLoading.postValue(false)
            }

            override fun onFailure(call: Call<Result>, t: Throwable) {
                _isLoading.postValue(false)
            }
        })
    }
}