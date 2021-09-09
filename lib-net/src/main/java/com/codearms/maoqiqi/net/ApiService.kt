package com.codearms.maoqiqi.net

import retrofit2.http.GET
import retrofit2.http.HeaderMap
import retrofit2.http.Url

interface ApiService {

    @GET
    fun getData(@Url url: String, @HeaderMap headers: Map<String, String>?)
}