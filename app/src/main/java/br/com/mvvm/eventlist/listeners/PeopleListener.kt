package br.com.mvvm.eventlist.listeners

import br.com.mvvm.eventlist.models.EventPeople

interface PeopleListener {
    fun onPeopleClicked(eventPeople: EventPeople?)
}