/*
 *
 *  * code:<br />Copyright (C) 2018 SIRIN LABS AG
 *
 */

package com.sirinlabs.walletconnectionsdk.entities

import android.os.Parcel
import android.os.Parcelable

data class SendRequestEntity(val recipient : String, val amount: Double,
                             val orderId : String? = null, val coinType : String = "ETH",
                             val contractData : String = "", val gasLimit : Double? = null,
                             val gasPrice : Double? = null,  val appData : AppData? = null) : Parcelable {
    constructor(parcel: Parcel) : this(
            parcel.readString(),
            parcel.readDouble(),
            parcel.readString(),
            parcel.readString(),
            parcel.readString(),
            parcel.readValue(Double::class.java.classLoader) as? Double,
            parcel.readValue(Double::class.java.classLoader) as? Double,
            parcel.readParcelable(AppData::class.java.classLoader))

    constructor(recipient : String, amount: Double) : this(recipient = recipient, amount = amount, coinType = "ETH")

    constructor(recipient : String, amount: Double, contractData: String) : this(recipient = recipient, amount = amount, coinType = "ETH", contractData = contractData)

    constructor(recipient : String, amount: Double, contractData: String, gasLimit: Double?, gasPrice : Double?) : this(recipient = recipient, amount = amount, coinType = "ETH",
                                                                                                                  contractData = contractData, gasLimit = gasLimit,
                                                                                                                  gasPrice = gasPrice)

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(recipient)
        parcel.writeDouble(amount)
        parcel.writeString(orderId)
        parcel.writeString(coinType)
        parcel.writeString(contractData)
        parcel.writeValue(gasLimit)
        parcel.writeValue(gasPrice)
        parcel.writeParcelable(appData, flags)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<SendRequestEntity> {
        override fun createFromParcel(parcel: Parcel): SendRequestEntity {
            return SendRequestEntity(parcel)
        }

        override fun newArray(size: Int): Array<SendRequestEntity?> {
            return arrayOfNulls(size)
        }
    }
}
