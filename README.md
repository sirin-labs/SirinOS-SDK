# SirinWalletSDK
SirinWalletSDK is an android library that wraps the connection to Finney wallet's api.

The sdk should be used by any third party app that need to communicate with Finney wallet.

# Upcoming features!
* sign transaction support

### Pre requirements
*   finney wallet app installed on the device

### Gradle
    implementation 'org.bitbucket.SirinOS:Wallet-SDK:0.0.1'
jitpack authentication required

### Usage
Init the sdk in your activity onCreate, and add callback when initialization complete
##

        WalletCommunicationManager.addFinishInitListener {
            // Your code
        }

        WalletCommunicationManager.init(applicationContext)

After the finish init callback is being fired, you can use any method in the api: 


get the deafult public address the user have in his wallet according to the coin type:

*   String getPublicAddress(String coinType)
    
     
    
        WalletCommunicationManager.getPublicAddress("ETH")

get the rpc address that being used for the coin type: 

*   String getRpcAddress(String coinType)
    
    
    
        WalletCommunicationManager.getRpcAddress("ETH")

get the chain id that being used for the coin type:

*   int getChainId(String coinType)
    
    
    
        WalletCommunicationManager.getChainId("ETH")

send transaction - open the wallet with the coin type, recipient, amount and data values that being passed via the sdk:

*   void sendTransaction(String coinType, String recipient, double value, String data, IWalletCommunicationCallback callback)
    
    

        val data = SendRequestEntity(recipient = "0x008023500DfB949b8854C329C6237bFC3c060Fd6", amount = 0.001)
        WalletCommunicationManager.sendTransaction(data, successMethod = { hash ->
            toastValue("Transaction Succeed : $hash")
        }, failureMethod = {err ->
            toastValue("Transaction Failed : $err")
        })

# Example Apps:
    https://bitbucket.org/SirinOS/wallet-sdk-demo/src

    getPublicAddress
    signTransaction
    sendTransaction
    startSigningMessage
    airDrop
    getRpcAddress
    getChainId
    getWalletId
    getPublicKey
    getSignedMessage

