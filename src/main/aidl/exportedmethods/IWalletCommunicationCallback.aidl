package exportedmethods;

oneway interface IWalletCommunicationCallback {

    void success(String hash);
     void failure(String erroreshrMessage);

}