package com.example.learning_andriod.domain

import android.content.IntentSender
import android.os.Parcel
import android.os.Parcelable
import java.util.Date

data class GmailItem(
    val id: String,
    val sender: String,
    val subtitle: String,
    val content: String,
    val date: Date,
) : Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readString()!!,
        parcel.readString()!!,
        parcel.readString()!!,
        parcel.readString()!!,
        Date(parcel.readLong())
    )

    override fun writeToParcel(p0: Parcel, p1: Int) {
        p0.writeString(id)
        p0.writeString(sender)
        p0.writeString(subtitle)
        p0.writeString(content)
        p0.writeLong(date.time)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<GmailItem> {
        override fun createFromParcel(p0: Parcel): GmailItem {
            return GmailItem(p0)
        }

        override fun newArray(size: Int): Array<GmailItem?> {
            return arrayOfNulls(size)
        }
    }
}
