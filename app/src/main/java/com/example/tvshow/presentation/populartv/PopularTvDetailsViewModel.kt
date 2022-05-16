package com.example.tvshow.presentation.populartv

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.tvshow.BuildConfig.API_KEY
import com.example.tvshow.data.network.ApiConfig
import com.example.tvshow.data.network.details.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class PopularTvDetailsViewModel: ViewModel(){

    //ngebungkus string
    private val _errorMessage = MutableLiveData<String>()
    val errorMessage: LiveData<String> = _errorMessage

    //live data (perantara /alat bantu) -> isinya tv details response
    private val _tvDetailsData = MutableLiveData<TvDetailsResponse>()
    val tvDetailsData: LiveData<TvDetailsResponse> = _tvDetailsData



    fun getTvDetailsById(tvId: String) {
        ApiConfig.getApiservice().getTvDetailsById(tvId, API_KEY).enqueue(
            object : Callback<TvDetailsResponse> {
                override fun onResponse
                            (call: Call<TvDetailsResponse>,
                             response: Response<TvDetailsResponse>,
                ) {
                    if (response.isSuccessful){
                        val responseBody = response.body()
                        if (responseBody != null){
                            val data = responseBody
                            _tvDetailsData.postValue(data)
                        }
                    }else{
                        Log.e(TAG, "onResponseError : ${response.message()}")
                        _errorMessage.postValue("Error displaying details popular tv")
                    }
                }

                override fun onFailure(call: Call<TvDetailsResponse>, t: Throwable) {
                    Log.e(TAG, "onFailure ${t.message}")
                    Log.e(TAG, Log.getStackTraceString(t))
                    _errorMessage.postValue(t.message)
                }

            }
        )
    }
    companion object {
        private val TAG = PopularTvDetailsViewModel::class.simpleName
    }

}