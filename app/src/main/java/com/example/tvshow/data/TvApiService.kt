package com.example.tvshow.data

import com.example.tvshow.data.network.details.TvDetailsResponse
import com.example.tvshow.data.network.response.TvResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface TvApiService {

    @GET("tv/popular?")
    fun getPopularTv(
        @Query("api_key") apiKey: String
    ): Call<TvResponse>

    @GET("tv/on_the_air?")
    fun getOnAirTv(
        @Query("api_key") apiKey: String
    ): Call<TvResponse>

    @GET("tv/airing_today?")
    fun getTodayTv(
        @Query("api_key") apiKey: String
    ): Call<TvResponse>

    @GET("tv/{tv_id}")
    fun getTvDetailsById(
        @Path("tv_id") tvId: String,
        @Query("api_key") apiKey: String
    ): Call<TvDetailsResponse>

}