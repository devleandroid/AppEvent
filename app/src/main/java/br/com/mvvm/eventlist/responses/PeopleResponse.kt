package br.com.mvvm.eventlist.responses

import br.com.mvvm.eventlist.models.EventPeople
import com.google.gson.annotations.SerializedName

class PeopleResponse {

    @SerializedName("events")
    private var eventPeople: EventPeople? = null

    fun getPeople(): EventPeople? {
        return eventPeople
    }
}