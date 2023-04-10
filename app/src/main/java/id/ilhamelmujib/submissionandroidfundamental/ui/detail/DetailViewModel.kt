package id.ilhamelmujib.submissionandroidfundamental.ui.detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import id.ilhamelmujib.submissionandroidfundamental.model.Detail
import id.ilhamelmujib.submissionandroidfundamental.service.ApiConfig
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class DetailViewModel: ViewModel() {

    private val _detail = MutableLiveData<Detail>()
    val detail: LiveData<Detail> = _detail

    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean> = _isLoading


    fun detailUser(username: String) {
        _isLoading.postValue(true)
        val client = ApiConfig.getApiService().detailUser(username)
        client.enqueue(object : Callback<Detail> {
            override fun onResponse(call: Call<Detail>, response: Response<Detail>) {
                response.body()?.let {
                    _detail.postValue(it)
                }
                _isLoading.postValue(false)
            }

            override fun onFailure(call: Call<Detail>, t: Throwable) {
                _isLoading.postValue(false)
            }
        })
    }


}