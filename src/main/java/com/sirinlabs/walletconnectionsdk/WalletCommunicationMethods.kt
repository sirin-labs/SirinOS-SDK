/*
 *
 *  * code:<br />Copyright (C) 2018 SIRIN LABS AG
 *
 */

package com.sirinlabs.walletconnectionsdk

import android.content.ComponentName
import android.content.ServiceConnection
import android.os.IBinder
import exportedmethods.IWalletByteResultCallback
import exportedmethods.IWalletCommunication
import exportedmethods.IWalletCommunicationCallback
import java.util.ArrayList

class WalletCommunicationMethods {

    companion object {
        const val WALLET_PACKAGE = "com.sirinlabs.os.wallet"
        const val WALLET_CONNECTION_SERVICE_PACKAGE = "$WALLET_PACKAGE.services.walletcommunication.WalletCommunicationService"
    }

    private val TAG = WalletCommunicationMethods::class.java.name


    private var mListeners : List<() -> Unit> = ArrayList()

    fun addFinishInitListener(listener : () -> Unit) {
        mListeners = mListeners.plus(listener)
    }

    fun disconnect() {
        mConnection?.onServiceDisconnected()
    }

    fun informFinishInit() {
        mListeners.map {
            it.invoke()
        }
    }

    var mService: IWalletCommunication? = null

    fun getPublicAddress(coinType: String): String {
        return mService?.getPublicAddress(coinType) ?: ""
    }

    fun sendTransaction(coinType: String, recipient: String, amount : Double, data: String = "",gasLimit : Double = 0.0, gasPrice : Double = 0.0, successMethod : (hash : String) -> Unit, failureMethod :(errorMessage : String) -> Unit) {

        val callback = object:IWalletCommunicationCallback.Stub() {
            override fun success(hash : String) {
                successMethod(hash)
            }

            override fun failure(errorMessage: String) {
                failureMethod(errorMessage)
            }

            override fun asBinder(): IBinder? {
                return this
            }
        }

        mService?.sendTransaction(coinType, recipient, amount, data, gasLimit, gasPrice, callback)
    }

    fun startSigningMessage(packageName : String,desc: String, indShowASCII : Boolean = true, successMethod : (result : ByteArray) -> Unit, failureMethod :(errorMessage : String) -> Unit) {

        val callback = object: IWalletByteResultCallback.Stub() {
            override fun success(result: ByteArray?) {
                result?.let(successMethod)
            }

            override fun failure(errorMessage: String) {
                failureMethod(errorMessage)
            }

            override fun asBinder(): IBinder? {
                return this
            }
        }

        mService?.startSigningMessageASCII(packageName, desc, indShowASCII, callback)
    }

    fun airDrop(desc: String, successMethod : (result : ByteArray) -> Unit, failureMethod :(errorMessage : String) -> Unit) {

        val callback = object: IWalletByteResultCallback.Stub() {
            override fun success(result: ByteArray?) {
                result?.let(successMethod)
            }

            override fun failure(errorMessage: String) {
                failureMethod(errorMessage)
            }

            override fun asBinder(): IBinder? {
                return this
            }
        }

        mService?.airDrop(desc, callback)
    }

    fun getRpcAddress(coinType: String): String {
        return mService?.getRpcAddress(coinType) ?: ""
    }

    fun getChainId(coinType: String): Int {
        return mService?.getChainId(coinType) ?: -1
    }

    fun getWalletId(): String = mService?.walletId ?: ""


    fun getPublicKey(): String = mService?.publicKey ?: ""


    val mConnection = object : ServiceConnection {

        override fun onServiceConnected(className: ComponentName, service: IBinder) {
            mService = IWalletCommunication.Stub.asInterface(service)
            informFinishInit()

        }

        override fun onServiceDisconnected(className: ComponentName) {
            mService = null
        }
    }
}
