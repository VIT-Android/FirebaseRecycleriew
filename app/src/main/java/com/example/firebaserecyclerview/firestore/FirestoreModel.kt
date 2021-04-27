package com.example.firebaserecyclerview.firestore

import android.os.Parcel
import android.os.Parcelable

/**
 * Created by Avinash Vijayvargiya on 27-04-2021.
 */
class FirestoreModel : Parcelable {
    lateinit var name: String
    lateinit var city: String
    lateinit var state: String

    constructor(parcel: Parcel) : this() {
        name = parcel.readString().toString()
        city = parcel.readString().toString()
        state = parcel.readString().toString()
    }

    // Required for Firebase
    constructor()

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(name)
        parcel.writeString(city)
        parcel.writeString(state)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<FirestoreModel> {
        override fun createFromParcel(parcel: Parcel): FirestoreModel {
            return FirestoreModel(parcel)
        }

        override fun newArray(size: Int): Array<FirestoreModel?> {
            return arrayOfNulls(size)
        }
    }
}