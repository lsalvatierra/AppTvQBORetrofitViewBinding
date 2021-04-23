package com.qbo.apptvqboretrofitviewbinding.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.qbo.apptvqboretrofitviewbinding.R
import com.qbo.apptvqboretrofitviewbinding.adapter.PersonajeAdapter
import com.qbo.apptvqboretrofitviewbinding.api.ApiPersonajeService
import com.qbo.apptvqboretrofitviewbinding.databinding.ActivityMainBinding
import com.qbo.apptvqboretrofitviewbinding.model.Personaje
import com.qbo.apptvqboretrofitviewbinding.model.ResultPersonaje
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var apiservice: ApiPersonajeService

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val retrofit: Retrofit = Retrofit.Builder()
            .baseUrl("https://rickandmortyapi.com/api/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        binding.rvpersonaje.layoutManager = LinearLayoutManager(this)
        apiservice = retrofit.create<ApiPersonajeService>(ApiPersonajeService::class.java)
        obtenerPersonajes()
    }

    private fun obtenerPersonajes() {
        apiservice.obtenerPersonajes().enqueue(object : Callback<ResultPersonaje>{
            override fun onResponse(
                call: Call<ResultPersonaje>,
                response: Response<ResultPersonaje>
            ) {
                val listaPersonajes = response.body()!!.results
                binding.rvpersonaje.apply {
                    adapter = PersonajeAdapter(listaPersonajes)
                }

            }

            override fun onFailure(call: Call<ResultPersonaje>, t: Throwable) {
                TODO("Not yet implemented")
            }

        })
    }
}