package com.example.ecommerceapp.model

import android.os.Parcel
import android.os.Parcelable


data class ItemsModel(
    var description: String = "",
    var picUrl: List<String> = ArrayList(),
    var price: Double = 0.0,
    var rating: Double = 0.0,
    var size: List<String> = ArrayList(),
    var title: String = "",
    var numberInCart: Int = 0
) : Parcelable{
    constructor(parcel: Parcel): this(
        parcel.readString().toString(),
        parcel.createStringArrayList() as ArrayList<String>,
        parcel.readDouble(),
        parcel.readDouble(),
        parcel.createStringArrayList() as ArrayList<String>,
        parcel.readString().toString(),
        parcel.readInt()
        )

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(description)
        parcel.writeStringList(picUrl)
        parcel.writeDouble(price)
        parcel.writeDouble(rating)
        parcel.writeStringList(size)
        parcel.writeString(title)
        parcel.writeInt(numberInCart)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<ItemsModel> {
        override fun createFromParcel(parcel: Parcel): ItemsModel {
            return ItemsModel(parcel)
        }

        override fun newArray(size: Int): Array<ItemsModel?> {
            return arrayOfNulls(size)
        }
    }
}
