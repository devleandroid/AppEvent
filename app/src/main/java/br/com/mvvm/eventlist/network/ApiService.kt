package br.com.mvvm.eventlist.network

import androidx.lifecycle.MutableLiveData
import br.com.mvvm.eventlist.models.EventPeople
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {

    @GET("events")
    fun getEventPeople(): Call<MutableList<EventPeople>>

    @GET("events/")
    fun getDetailsEvent(@Query("id") idEvent: String): Call<MutableList<EventPeople>>
}