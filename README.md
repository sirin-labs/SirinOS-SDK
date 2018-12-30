# SirinWalletSDK
SirinWalletSDK is an android library that wraps the connection to Finney wallet's api.

The sdk should be used by any third party app that need to communicate with Finney wallet.

# Upcoming features!
* sign transaction support

### Pre requirements
*   finney wallet app installed on the device

### Gradle
    implementation 'org.bitbucket.SirinOS:Wallet-SDK:1.0.1'
jitpack authentication required

### Usage
Init the sdk in your activity onCreate, and add callback when initialization complete
##

        WalletSDKManager.addFinishInitListener {
            // Your code
        }

        WalletSDKManager.init(applicationContext)

After the finish init callback is being fired, you can use any method in the api: 


get the deafult public address the user have in his wallet according to the coin type:

*   String getPublicAddress(String coinType)
    
     
    
        WalletSDKManager.getPublicAddress("ETH")

get the rpc address that being used for the coin type: 

*   String getRpcAddress(String coinType)
    
    
    
        WalletSDKManager.getRpcAddress("ETH")

get the chain id that being used for the coin type:

*   int getChainId(String coinType)
    
    
    
        WalletSDKManager.getChainId("ETH")

send transaction - open the wallet with the coin type, recipient, amount and data values that being passed via the sdk:

*   void sendTransaction(String coinType, String recipient, double value, String data, IWalletCommunicationCallback callback)
    


        val data = SendRequestEntity(recipient = "0x008023500DfB949b8854C329C6237bFC3c060Fd6", amount = 0.001) // amount range is 10^17 > value > 10^-10, recipient should be valid bitcoin/ether address
        WalletCommunicationManager.sendTransaction(data, successMethod = { hash ->
            toastValue("Transaction Succeed : $hash")
        }, failureMethod = {err ->
            toastValue("Transaction Failed : $err")
        })


airdrop - get message signed by device key for airdrop registration, the returned message encoded in der

*   void airDrop(String desc, IWalletByteResultCallback callback);


    fun airDrop() {
        WalletSDKManager.airDrop("lorem ipson lorem ipson lorem ipson lorem ipson lorem ipson lorem ipson lorem ipson lorem ipson", successMethod = {signature ->
            toastValue("Transaction Succeed : ${byteArrayToHexString(signature)}")
            Log.d(TAG, "airdrop_der = ${byteArrayToHexString(signature)}")
        }, failureMethod = {err ->
            toastValue("Transaction Failed : $err")
        })
    }

signing personal/public message - get message signed by device key for dapps registration(personal or public message)

    *   void startSigningMessage(String desc, IWalletByteResultCallback callback);


    fun startSigningMessage() {
        WalletSDKManager.startSigningMessage(signed_message_txt.text.toString(),
                "lorem ipson lorem ipson lorem ipson lorem ipson lorem ipson lorem ipson lorem ipson lorem ipson ",
                successMethod = {signature ->
            toastValue("Transaction Succeed : ${byteArrayToHexString(signature)}")
        }, failureMethod = {err ->
            toastValue("Transaction Failed : $err")
        })
    }

# Example Apps:
    https://github.com/sirin-labs/SirinOS-SDK-DemoApp

