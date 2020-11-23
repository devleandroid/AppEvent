package br.com.mvvm.eventlist.network

import br.com.mvvm.eventlist.models.EventPeople
import retrofit2.Call
import retrofit2.http.*

interface ApiService {

    @GET("events")
    fun getEventPeople(): Call<MutableList<EventPeople>>

    @GET("events/")
    fun getDetailsEvent(@Query("id") idEvent: String): Call<MutableList<EventPeople>>

    @FormUrlEncoded
    @POST("checkin")
    fun postCheckin(
        @Field("eventId") eventId: String,
        @Field("name") name: String,
        @Field("email") email: String
    ): Call<MutableList<EventPeople>>
}