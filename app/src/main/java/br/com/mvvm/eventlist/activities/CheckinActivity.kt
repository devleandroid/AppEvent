package br.com.mvvm.eventlist.activities

import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProviders
import br.com.mvvm.eventlist.R
import br.com.mvvm.eventlist.adapters.ImageSliderAdapter
import br.com.mvvm.eventlist.databinding.ActivityCheckinBinding
import br.com.mvvm.eventlist.models.EventPeople
import br.com.mvvm.eventlist.viewmodels.CheckinViewModel
import br.com.mvvm.eventlist.viewmodels.CheckinViewModelFactory

class CheckinActivity : AppCompatActivity() {

    private var activityCheckinBinding: ActivityCheckinBinding? = null
    private var event:  MutableList<EventPeople>? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        activityCheckinBinding = DataBindingUtil.setContentView(this, R.layout.activity_checkin)
        doInitialization()
    }

    private fun doInitialization() {
        var idEvent: Int = intent.getIntExtra("id", 0)

        val txtTitle = findViewById<TextView>(R.id.txtTitle)

        val checkinViewModel =
            ViewModelProviders.of(this, CheckinViewModelFactory(this, idEvent)).get(
                CheckinViewModel::class.java
            )

        event = mutableListOf<EventPeople>()

        activityCheckinBinding!!.isLoading = true

        checkinViewModel!!.getData().observe(this, { t ->
            t?.let {
                event!!.addAll(it)
                event!![0].image?.let { image -> loadImageSlider(image) }
                txtTitle.text = event!![0].title
            }
        })
    }

    private fun loadImageSlider(sliderImages: String) {
        activityCheckinBinding!!.sliderViewPager.adapter = ImageSliderAdapter(sliderImages)
        activityCheckinBinding!!.sliderViewPager.visibility = View.VISIBLE
        activityCheckinBinding!!.viewFadingEdge.visibility = View.VISIBLE
        activityCheckinBinding!!.layoutSliderIndicators.visibility = View.VISIBLE
        activityCheckinBinding!!.isLoading = false
    }

}