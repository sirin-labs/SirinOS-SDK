/*
 *
 *  * code:<br />Copyright (C) 2018 SIRIN LABS AG
 *
 */

package com.sirinlabs.walletconnectionsdk.entities

import android.os.Parcel
import android.os.Parcelable

data class AppData(val applicationId : String, val dAppName : String, val dAppUrl : String, val dAppIcon : String) : Parcelable {
    constructor(parcel: Parcel) : this(
            parcel.readString(),
            parcel.readString(),
            parcel.readString(),
            parcel.readString())

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(applicationId)
        parcel.writeString(dAppName)
        parcel.writeString(dAppUrl)
        parcel.writeString(dAppIcon)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<AppData> {
        override fun createFromParcel(parcel: Parcel): AppData {
            return AppData(parcel)
        }

        override fun newArray(size: Int): Array<AppData?> {
            return arrayOfNulls(size)
        }
    }
}