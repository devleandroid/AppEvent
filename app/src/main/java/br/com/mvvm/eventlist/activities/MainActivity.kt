package br.com.mvvm.eventlist.activities

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import br.com.mvvm.eventlist.R
import br.com.mvvm.eventlist.adapters.EventAdapter
import br.com.mvvm.eventlist.databinding.ActivityMainBinding
import br.com.mvvm.eventlist.listeners.PeopleListener

import br.com.mvvm.eventlist.models.EventPeople
import br.com.mvvm.eventlist.viewmodels.EventPeopleViewModel
import br.com.mvvm.eventlist.viewmodels.EventPeopleViewModelFactory

class MainActivity : AppCompatActivity(), PeopleListener {

    private lateinit var listEventPeople: MutableList<EventPeople>
    private lateinit var adapter: EventAdapter
    lateinit var recyclerView: RecyclerView
    private var activityMainBinding: ActivityMainBinding? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        activityMainBinding = DataBindingUtil.setContentView(this, R.layout.activity_main)

        doInitialization()
    }

    private fun doInitialization() {
        activityMainBinding!!.isLoading = true
        recyclerView = findViewById(R.id.people_recycler_view)
        recyclerView.layoutManager = LinearLayoutManager(this@MainActivity)
        listEventPeople = mutableListOf<EventPeople>()
        adapter = EventAdapter(
            this,
            listEventPeople,
            this
        )
        recyclerView.adapter = adapter

        val eventViewModel = ViewModelProviders.of(this, EventPeopleViewModelFactory(this)).get(
            EventPeopleViewModel::class.java
        )

        eventViewModel.getData().observe(this, { t ->
            listEventPeople.clear()
            t?.let {
                listEventPeople.addAll(it)
            }
            adapter.notifyDataSetChanged()
            activityMainBinding!!.isLoading = false
        })
    }

    override fun onPeopleClicked(eventPeople: EventPeople?) {
        val intent = Intent(applicationContext, DetailCheckinActivity::class.java)
        intent.putExtra("id", eventPeople!!.id.toString())
        intent.putExtra("title", eventPeople!!.title)
        intent.putExtra("description", eventPeople.description)
        intent.putExtra("latitude", eventPeople.latitude)
        intent.putExtra("longitude", eventPeople.longitude)
        startActivity(intent)
    }

}