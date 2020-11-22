package br.com.mvvm.eventlist.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import br.com.mvvm.eventlist.R
import br.com.mvvm.eventlist.databinding.ItemContainerSliderImageBinding

class ImageSliderAdapter(private var sliderImage: String) :
    RecyclerView.Adapter<ImageSliderAdapter.ViewHolder>() {
    private var sliderImages: Array<String> = arrayOf(sliderImage)
    private var layoutInflater: LayoutInflater? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        if (layoutInflater == null) {
            layoutInflater = LayoutInflater.from(parent.context)
        }
        val sliderImageBinding: ItemContainerSliderImageBinding = DataBindingUtil.inflate(
            layoutInflater!!, R.layout.item_container_slider_image, parent, false
        )
        return ViewHolder(sliderImageBinding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindSliderImage(sliderImages[position])
    }

    override fun getItemCount(): Int {
        return sliderImages.size
    }


    class ViewHolder(itemContainerSliderImageBinding: ItemContainerSliderImageBinding) :
        RecyclerView.ViewHolder(itemContainerSliderImageBinding.getRoot()) {
        private val itemContainerSliderImageBinding: ItemContainerSliderImageBinding =
            itemContainerSliderImageBinding

        fun bindSliderImage(imageUrl: String?) {
            itemContainerSliderImageBinding.imageURL = imageUrl
        }

    }
}