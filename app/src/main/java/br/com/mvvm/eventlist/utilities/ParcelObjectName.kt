package br.com.mvvm.eventlist.utilities

import android.os.Parcel
import android.os.Parcelable

class ParcelObjectName (val title:String, val description:String) : Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readString()!!,
        parcel.readString()!!)

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(title)
        parcel.writeString(description)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<ParcelObjectName> {
        override fun createFromParcel(parcel: Parcel): ParcelObjectName {
            return ParcelObjectName(parcel)
        }

        override fun newArray(size: Int): Array<ParcelObjectName?> {
            return arrayOfNulls(size)
        }
    }
}