<!DOCTYPE html><html><head><meta charset="utf-8"><title>Readme.md</title><style></style></head><body id="preview">
<h1><a id="SirinWalletSDK_0"></a>SirinWalletSDK</h1>
<h6><a id="SirinWalletSDK_is_an_android_library_that_wraps_the_connection_to_Finney_wallets_api_1"></a>SirinWalletSDK is an android library that wraps the connection to Finney wallet’s api.</h6>
<h6><a id="The_sdk_should_be_used_by_any_third_party_app_that_need_to_communicate_with_Finney_wallet_2"></a>The sdk should be used by any third party app that need to communicate with Finney wallet.</h6>
<h1><a id="Upcoming_features_4"></a>Upcoming features!</h1>
<ul>
<li>sign transaction support</li>
</ul>
<h3><a id="Pre_requirements_7"></a>Pre requirements</h3>
<ul>
<li>finney wallet app installed on the device</li>
</ul>
<h3><a id="Gradle_10"></a>Gradle</h3>
<pre><code>implementation 'org.bitbucket.SirinOS:Wallet-SDK:0.0.1'
</code></pre>
<h5><a id="jitpack_authentication_required_12"></a>*jitpack authentication required</h5>
<h3><a id="Usage_14"></a>Usage</h3>
<h6><a id="Init_the_sdk_in_your_activity_onCreate_and_add_callback_when_initialization_complete_15"></a>Init the sdk in your activity onCreate, and add callback when initialization complete</h6>
<h2><a id="_16"></a></h2>
<pre><code>    WalletCommunicationManager.addFinishInitListener {
        initButtons()
    }

    WalletCommunicationManager.init(applicationContext)
</code></pre>
<h4><a id="After_the_finish_init_callback_is_being_fired_you_can_use_any_method_in_the_api_24"></a>After the finish init callback is being fired, you can use any method in the api:</h4>
<ul>
<li>String getPublicAddress(String coinType)</li>
<li>String getRpcAddress(String coinType)</li>
<li>int getChainId(String coinType)</li>
<li>void sendTransaction(String coinType, String recipient, double value, String data, IWalletCommunicationCallback callback)</li>
</ul>
<h1><a id="Example_Apps_30"></a>Example Apps:</h1>
<pre><code>https://bitbucket.org/SirinOS/wallet-sdk-demo/src
</code></pre>

</body></html>