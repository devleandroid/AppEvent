package br.com.mvvm.eventlist.viewmodels

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class CheckinViewModelFactory (private val context: Context, private val idEvent: Int) : ViewModelProvider.NewInstanceFactory() {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return CheckinViewModel(context, idEvent) as T
    }
}