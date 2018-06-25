package de.berlindroid.droidcon.droidconcommunity

import okhttp3.Call
import retrofit2.http.GET
import retrofit2.http.Path

const val SERVER_URL = "https://cfp.droidcon.de/rest/sessions.json"

interface GitHubService {
    @GET("users/{user}/repos")
    fun listRepos(@Path("user") user: String): Call<List<Session>>
}