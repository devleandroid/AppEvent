package br.com.mvvm.eventlist.viewmodels

import android.content.Context
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import br.com.mvvm.eventlist.models.EventPeople
import br.com.mvvm.eventlist.repositories.EventPeopleRepository
import br.com.mvvm.eventlist.utilities.Utility.isInternetAvailable

class EventPeopleViewModel (private val context: Context) : ViewModel() {

    private var listData = MutableLiveData<ArrayList<EventPeople>>()

    init{
        val eventPeopleRepository : EventPeopleRepository by lazy {
            EventPeopleRepository
        }
        if(context.isInternetAvailable()) {
            listData = eventPeopleRepository.getMutableLiveData(context)
        }
    }

    fun getData() : MutableLiveData<ArrayList<EventPeople>>{
        return listData
    }
}