package br.com.mvvm.eventlist.activities

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import br.com.mvvm.eventlist.R
import br.com.mvvm.eventlist.databinding.ActivityCheckinBinding
import br.com.mvvm.eventlist.models.EventPeople
import br.com.mvvm.eventlist.viewmodels.CheckinViewModel
import br.com.mvvm.eventlist.viewmodels.CheckinViewModelFactory

class CheckinActivity : AppCompatActivity() {

    private var activityCheckinBinding: ActivityCheckinBinding? = null
    private var event:  MutableList<EventPeople>? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //setContentView(R.layout.activity_checkin)
        activityCheckinBinding = DataBindingUtil.setContentView(this, R.layout.activity_checkin)
        doInitialization()
    }

    private fun doInitialization() {
        var idEvent: Int = intent.getIntExtra("id", 0)
        val checkinViewModel =
            ViewModelProviders.of(this, CheckinViewModelFactory(this, idEvent)).get(
                CheckinViewModel::class.java
            )

        event = mutableListOf<EventPeople>()


        checkinViewModel!!.getData().observe(this, { t ->
            t?.let {
                event!!.addAll(it)
            }
        })
    }
}