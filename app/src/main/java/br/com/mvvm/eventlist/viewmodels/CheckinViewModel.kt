package br.com.mvvm.eventlist.viewmodels

import android.content.Context
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import br.com.mvvm.eventlist.models.EventPeople
import br.com.mvvm.eventlist.repositories.CheckinRepository
import br.com.mvvm.eventlist.utilities.Utility.isInternetAvailable

class CheckinViewModel(
    private val context: Context,
    private val idEvent: String,
    private val name: String,
    private val email: String
) : ViewModel() {

    private var listData = MutableLiveData<ArrayList<EventPeople>>()

    init {
        val checkinRepository: CheckinRepository by lazy {
            CheckinRepository
        }
        if (context.isInternetAvailable()) {
            listData = checkinRepository.getMutableLiveData(context, idEvent, name, email)
        }
    }

    fun getData() : MutableLiveData<ArrayList<EventPeople>> {
        return listData
    }
}