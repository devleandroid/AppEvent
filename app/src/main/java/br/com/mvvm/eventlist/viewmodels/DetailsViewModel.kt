package br.com.mvvm.eventlist.viewmodels

import android.content.Context
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import br.com.mvvm.eventlist.models.EventPeople
import br.com.mvvm.eventlist.repositories.DetailsRepository
import br.com.mvvm.eventlist.utilities.Utility.isInternetAvailable

class DetailsViewModel(private val context: Context, private val idEvent: String) : ViewModel() {

    private var listData = MutableLiveData<ArrayList<EventPeople>>()

    init {
        val checkinRepository: DetailsRepository by lazy {
            DetailsRepository
        }
        if (context.isInternetAvailable()) {
            listData = checkinRepository.getMutableLiveData(context, idEvent)
        }
    }

    fun getData(): MutableLiveData<ArrayList<EventPeople>> {
        return listData
    }
}