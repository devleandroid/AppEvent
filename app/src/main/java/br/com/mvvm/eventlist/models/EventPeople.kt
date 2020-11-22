package br.com.mvvm.eventlist.models

import com.google.gson.annotations.SerializedName

data class EventPeople(
        @SerializedName("people")
    val peoples : List<String>? = null,

        @SerializedName("date")
    val date : Int? = null,

        @SerializedName("description")
    val description : String? = null,

        @SerializedName("image")
    val image : String? = null,

        @SerializedName("longitude")
    val longitude : Double? = null,

        @SerializedName("latitude")
    val latitude : Double? = null,

        @SerializedName("price")
    val price : Double? = null,

        @SerializedName("title")
    val title : String? = null,

        @SerializedName("id")
    val id : Int? = null
)  {
}