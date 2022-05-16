package com.example.tvshow.presentation.today

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.tvshow.BuildConfig
import com.example.tvshow.data.network.ApiConfig
import com.example.tvshow.data.network.response.ResultsItem
import com.example.tvshow.data.network.response.TvResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class TodayTvViewModel : ViewModel() {
    private val _errorMessage = MutableLiveData<String>()
    val errorMessage: LiveData<String> = _errorMessage

    private val _listTvData = MutableLiveData<List<ResultsItem>>()
    val listTvData: LiveData<List<ResultsItem>> = _listTvData

    fun getTodayTv(){
        ApiConfig.getApiservice().getTodayTv(BuildConfig.API_KEY).enqueue(
            object : Callback<TvResponse> {
                override fun onResponse
                            (call: Call<TvResponse>,
                             response: Response<TvResponse>,
                ) {
                    if (response.isSuccessful){
                        val responseBody = response.body()
                        if (responseBody != null){
                            val data = responseBody.results
                            _listTvData.postValue(data)
                        }
                    }else{
                        Log.e(TAG, "onResponseError : ${response.message()}")
                        _errorMessage.postValue("Error displaying list today tv")
                    }
                }

                override fun onFailure(call: Call<TvResponse>, t: Throwable) {
                    Log.e(TAG, "onFailure ${t.message}")
                    Log.e(TAG, Log.getStackTraceString(t))
                    _errorMessage.postValue(t.message)
                }

            }
        )
    }
    companion object {
        private val TAG = TodayTvViewModel::class.simpleName
    }
}