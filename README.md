
<img src="https://sirinlabs.com/wp-content/uploads/2018/10/logo-black.svg">


# SirinWalletSDK   

SirinWalletSDK is an android library that wraps the connection to Finney wallet's api.  
The sdk should be used by any third party app that need to communicate with Finney wallet.  
  
# Upcoming features!  
  * :currency_exchange: Token change service integration 
  *  :credit_card: Credit card to crypto integration
### Pre requirements  :unlock:
  Finney wallet app installed on the device  
  
###  Gradle    :hammer:
   ```gradle
 implementation "com.github.sirin-labs:SirinOS-SDK:1.0.2"
   
   // add this in project scope build file 
   repositories {
	   // repos
	   maven { url "https://jitpack.io" } 
  }  
 ```
  
### Usage  :briefcase:
  
Init the sdk in your activity onCreate, and add callback when initialization complete:
  
  ```kotlin             
WalletSDKManager.addFinishInitListener { // Your code }  
WalletSDKManager.init(applicationContext)  
``` 
After the finish init callback is being fired, you can use any method in the api:   
  
  
**Public Address** - Get the deafult public address the user have in his wallet according to the coin type:  
*  String getPublicAddress(String coinType)
 
  ```kotlin   
WalletSDKManager.getPublicAddress("ETH")  
```       
       
      
**RPC Address** - Get the rpc address that being used for the coin type:
* String getRpcAddress(String coinType) 
```kotlin  
WalletSDKManager.getRpcAddress("ETH")      
 ```  

**Chain Id** - Get the chain id that being used for the coin type:  
* int getChainId(String coinType)  
 ```kotlin  
WalletSDKManager.getChainId("ETH")       
 ```   
      
**Send transaction** - open the wallet with the coin type, recipient, amount and data values that being passed via the sdk:  
  
* void sendTransaction(String coinType, String recipient, double value, String data, IWalletCommunicationCallback callback)  
      
  
 ```kotlin 
 val data = SendRequestEntity(recipient = "0x008023500DfB949b8854C329C6237bFC3c060Fd6",
						      amount = 0.001) 
 //amount range is 10^17 > value > 10^-10, recipient should be valid bitcoin/ether address 
WalletSDKManager.sendTransaction(data,
successMethod = { hash -> toastValue("Transaction Succeed : $hash") },
 failureMethod = {err -> toastValue("Transaction Failed : $err") })
 ```
**Airdrop** - get message signed by device key for airdrop registration, the returned message encoded in der  
  
* void airDrop(String desc, IWalletByteResultCallback callback);  
  
 ```kotlin
 fun airDrop() {   
	 WalletSDKManager.airDrop("description",
	  successMethod = {signature -> toastValue("Transaction Succeed : 
								       ${byteArrayToHexString(signature)}")}, 
	  failureMethod = {err -> toastValue("Transaction Failed : $err") }) 
	 }  
```
 * Signing personal/public message - get message signed by device key for dapps registration(personal or public message)
 ```kotlin
 fun startSigningMessage() { 
	 WalletSDKManager.startSigningMessage(signed_message_txt.text.toString(),
										     "desc ", 
										      successMethod = {signature ->
										      toastValue("Transaction Succeed : 
										      ${byteArrayToHexString(signature)}") },
										      failureMethod = {err ->
										      toastValue("Transaction Failed : $err") })
  }  
```
# Example Apps :iphone:  
 https://github.com/sirin-labs/SirinOS-SDK-DemoApp
