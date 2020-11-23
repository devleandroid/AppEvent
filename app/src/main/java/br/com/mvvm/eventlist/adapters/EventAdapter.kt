package br.com.mvvm.eventlist.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import br.com.mvvm.eventlist.R
import br.com.mvvm.eventlist.listeners.PeopleListener
import br.com.mvvm.eventlist.models.EventPeople
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.makeramen.roundedimageview.RoundedImageView

class EventAdapter (private val context: Context, private var list: MutableList<EventPeople>, private var peopleListener: PeopleListener) : RecyclerView.Adapter<EventAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(context)
        val view: View = inflater.inflate(R.layout.item_container_people,parent,false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val event = list.get(position)
        Glide.with(context).load(list.get(position).image)
            .apply(RequestOptions().centerCrop())
            .into(holder.imageEvent)
        holder.textTitle?.text = event?.title
        holder.view.setOnClickListener { peopleListener.onPeopleClicked(list[position]) }
        /*holder.textDescription?.text = event?.description
        holder.textDescription?.setOnClickListener {
                view -> peopleListener.onPeopleClicked(list[position])

        }*/

    }

    class ViewHolder(var view: View) : RecyclerView.ViewHolder(view){

        var imageEvent: RoundedImageView? = null
        var textTitle: TextView? = null
        //var textDescription: TextView? = null


        init {
            imageEvent = view.findViewById(R.id.imageEvent)
            textTitle = view.findViewById(R.id.textTitle)
            //textDescription = view.findViewById(R.id.textDescription)

        }

    }
}