package com.example.meucartaodevisitas.data.remote

import com.google.gson.annotations.SerializedName
import retrofit2.http.GET
import retrofit2.http.Path

data class RepoDto(
    val id: Long,
    val name: String,
    val description: String?,
    @SerializedName("html_url") val htmlUrl: String
)

interface ApiService {
    @GET("users/{user}/repos")
    suspend fun listUserRepos(@Path("user") user: String): List<RepoDto>
}
