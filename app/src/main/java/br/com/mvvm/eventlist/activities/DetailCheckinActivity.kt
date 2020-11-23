package br.com.mvvm.eventlist.activities

import android.app.AlertDialog
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.ProgressBar
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProviders
import br.com.mvvm.eventlist.R
import br.com.mvvm.eventlist.adapters.ImageSliderAdapter
import br.com.mvvm.eventlist.databinding.ActivityCheckinBinding
import br.com.mvvm.eventlist.models.EventPeople
import br.com.mvvm.eventlist.repositories.CheckinRepository
import br.com.mvvm.eventlist.viewmodels.AlertDialogView
import br.com.mvvm.eventlist.viewmodels.DetailViewModelFactory
import br.com.mvvm.eventlist.viewmodels.DetailsViewModel
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MapStyleOptions
import com.google.android.gms.maps.model.MarkerOptions
import com.google.android.material.textfield.TextInputEditText

class DetailCheckinActivity : AppCompatActivity(), OnMapReadyCallback {

    private var activityCheckinBinding: ActivityCheckinBinding? = null
    private var event: MutableList<EventPeople>? = null
    private var idEvent: String? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        activityCheckinBinding = DataBindingUtil.setContentView(this, R.layout.activity_checkin)
        doInitialization()
    }

    private fun doInitialization() {
        idEvent = intent.getStringExtra("id")

        val mapFragment = supportFragmentManager.findFragmentById(R.id.map) as SupportMapFragment
        val txtTitle = findViewById<TextView>(R.id.txtTitle)
        val txtDescription = findViewById<TextView>(R.id.txtDescription)


        mapFragment?.getMapAsync(this)
        val detailCheckinActivity =
            ViewModelProviders.of(this, DetailViewModelFactory(this, idEvent!!)).get(
                DetailsViewModel::class.java
            )

        event = mutableListOf<EventPeople>()

        activityCheckinBinding!!.isLoading = true

        detailCheckinActivity!!.getData().observe(this, { t ->
            t?.let {
                event!!.addAll(it)
                event!![0].image?.let { image -> loadImageSlider(image) }
                txtTitle.text = event!![0].title
                txtDescription.text = event!![0].description

            }
        })

        setupListeners()
    }

    private fun loadImageSlider(sliderImages: String) {
        activityCheckinBinding!!.sliderViewPager.adapter = ImageSliderAdapter(sliderImages)
        activityCheckinBinding!!.sliderViewPager.visibility = View.VISIBLE
        activityCheckinBinding!!.viewFadingEdge.visibility = View.VISIBLE
        activityCheckinBinding!!.layoutSliderIndicators.visibility = View.VISIBLE
        activityCheckinBinding!!.layoutDesciption.visibility = View.VISIBLE
        activityCheckinBinding!!.layoutLocalization.visibility = View.VISIBLE
        activityCheckinBinding!!.layoutCheckin.visibility = View.VISIBLE
        activityCheckinBinding!!.isLoading = false
    }

    override fun onMapReady(map: GoogleMap) {
        var latitude: Double = intent.getDoubleExtra("latitude", 0.0)
        var longitude: Double = intent.getDoubleExtra("longitude", 0.0)
        var title = intent.getStringExtra("title")

        if (latitude != null && longitude != null) {
            map.addMarker(
                MarkerOptions().position(
                    LatLng(
                        latitude,
                        longitude
                    )
                ).title(title)
            )
            map.moveCamera(CameraUpdateFactory.newLatLngZoom(LatLng(latitude, longitude), 18f))
            map.setMapStyle(
                MapStyleOptions.loadRawResourceStyle(
                    this@DetailCheckinActivity, R.raw.maps_style
                )
            )
        } else {
            activityCheckinBinding!!.layoutLocalization.visibility = View.GONE
        }
    }

    private fun setupListeners() {
        activityCheckinBinding!!.btnCheckIn.setOnClickListener {
            val dialogView = layoutInflater.inflate(R.layout.custom_dialog_fragment, null)
            val customDialog = AlertDialog.Builder(this)
                .setView(dialogView)
                .show()
            val enviar = dialogView.findViewById<Button>(R.id.btnEnviar)
            val name = dialogView.findViewById<TextInputEditText>(R.id.tiName)
            val email = dialogView.findViewById<TextInputEditText>(R.id.tiEmail)
            val progressBar = dialogView.findViewById<ProgressBar>(R.id.progressLoaderCheckIn)
            enviar.setOnClickListener {

                customDialog.dismiss()
                Toast.makeText(applicationContext, "Enviado com sucesso!", Toast.LENGTH_SHORT)
                    .show()
            }

        }
    }

    fun checkInAction(idEvent: String, name: String, email: String) {

        CheckinRepository.getMutableLiveData(this, idEvent, name, email).observe(
            this, { t ->
                t?.let {

                }
            }
        )
        AlertDialogView().dismiss()
    }
}