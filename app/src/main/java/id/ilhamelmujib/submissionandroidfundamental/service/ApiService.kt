package id.ilhamelmujib.submissionandroidfundamental.service

import id.ilhamelmujib.submissionandroidfundamental.model.Detail
import retrofit2.Call
import retrofit2.http.*
import id.ilhamelmujib.submissionandroidfundamental.model.Result
import id.ilhamelmujib.submissionandroidfundamental.model.User

interface ApiService {
    @GET("search/users")
    fun searchUser(
        @Query("q") username: String
    ): Call<Result>

    @GET("users/{username}")
    fun detailUser(
        @Path("username") username: String
    ): Call<Detail>

    @GET("users/{username}/{category}")
    fun followUser(
        @Path("username") username: String,
        @Path("category") category: String
    ): Call<List<User>>


}