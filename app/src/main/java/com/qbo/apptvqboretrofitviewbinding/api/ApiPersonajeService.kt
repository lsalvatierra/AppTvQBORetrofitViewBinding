package com.qbo.apptvqboretrofitviewbinding.api

import com.qbo.apptvqboretrofitviewbinding.model.ResultPersonaje
import retrofit2.Call
import retrofit2.http.GET

interface ApiPersonajeService {

    @GET("character")
    fun obtenerPersonajes(): Call<ResultPersonaje>
}