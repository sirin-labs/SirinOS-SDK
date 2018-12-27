/*
 *
 *  * code:<br />Copyright (C) 2018 SIRIN LABS AG
 *
 */

package com.sirinlabs.walletconnectionsdk

import android.content.ComponentName
import android.content.Context
import android.content.Intent
import com.sirinlabs.walletconnectionsdk.WalletCommunicationMethods.Companion.WALLET_CONNECTION_SERVICE_PACKAGE
import com.sirinlabs.walletconnectionsdk.WalletCommunicationMethods.Companion.WALLET_PACKAGE
import com.sirinlabs.walletconnectionsdk.entities.SendRequestEntity
import com.sirinlabs.walletconnectionsdk.exceptions.WalletServiceNotBindedException
import java.lang.ref.WeakReference

class WalletCommunicationManager {

    companion object {

        private lateinit var mContextRef: WeakReference<Context>

        private val methods: WalletCommunicationMethods by lazy {
            WalletCommunicationMethods()
        }

        // assign context and bind service, should get applicationcontext
        fun init(applicationContext: Context) {
            mContextRef = WeakReference(applicationContext)
            bindWalletService()
        }

        // fire when service binded
        fun addFinishInitListener(listener: () -> Unit) {
            methods.addFinishInitListener(listener)
        }

        fun isConnected(): Boolean = methods.mService != null

        // bind to the service in the wallet app using the context assigned in the initialize function
        private fun bindWalletService() {
            val serviceIntent = Intent()
                    .setComponent(ComponentName(
                            WALLET_PACKAGE,
                            WALLET_CONNECTION_SERVICE_PACKAGE))

            if (mContextRef.get() == null)
                return
            // Exception, not initialized or killed

            mContextRef.get()?.bindService(serviceIntent, methods.mConnection, Context.BIND_AUTO_CREATE)
        }

        fun forceBindWalletService() {
            bindWalletService()
        }

        fun checkConnected() {
            if (!isConnected()) {
                throw WalletServiceNotBindedException()
            }

        }

        /***********************************************************************************
         *************************exported fucntions****************************************
         ***********************************************************************************/

        fun getPublicAddress(coinType: String): String {
            return checkConnected().run { methods.getPublicAddress(coinType) }
        }

        fun sendTransaction(data : SendRequestEntity, successMethod: (hash : String) -> Unit, failureMethod: (errorMessage : String) -> Unit) {
            checkConnected().run {
                val amount = data.amount
                val recipient = data.recipient
                val coinType = data.coinType

                methods.sendTransaction(coinType, recipient, amount, data.contractData, data.gasLimit ?: 0.0, data.gasPrice ?: 0.0, successMethod, failureMethod)
            }
        }

        fun startSigningMessage(packageName : String,desc : String,indShowASCII : Boolean = true, successMethod : (result : ByteArray) -> Unit, failureMethod :(errorMessage : String) -> Unit) {
            checkConnected().run {
                methods.startSigningMessage(packageName,desc,indShowASCII, successMethod, failureMethod)
            }
        }

        fun airDrop(desc : String, successMethod : (result : ByteArray) -> Unit, failureMethod :(errorMessage : String) -> Unit) {
            checkConnected().run {
                methods.airDrop(desc,successMethod, failureMethod)
            }
        }

        fun getRpcAddress(coinType: String): String {
            return checkConnected().run {  methods.getRpcAddress(coinType)}
        }

        fun getChainId(coinType: String): Int {
            return checkConnected().run { methods.getChainId(coinType)}
        }

        fun getWalletId(): String =
            checkConnected().run {  methods.getWalletId()}


        fun getPublicKey(): String =
                checkConnected().run {  methods.getPublicKey()}

    }



}