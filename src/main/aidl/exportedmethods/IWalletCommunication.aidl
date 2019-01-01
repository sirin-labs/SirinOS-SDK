/*
 * code:<br />Copyright (C) 2018 SIRIN LABS AG
 */

package exportedmethods;

import exportedmethods.IWalletCommunicationCallback;
import exportedmethods.IWalletByteResultCallback;

// Interface declaring contract between third party to this app exported methods service
interface IWalletCommunication {

    String getPublicAddress(String coinType);
    void signTransaction(String coinType, String recipient, double value, String data, IWalletCommunicationCallback callback);
    void sendTransaction(String coinType, String recipient, double value, String data, double gasLimit, double gasPrice, IWalletCommunicationCallback callback);
    String getRpcAddress(String coinType);
    int getChainId(String coinType);
    String getWalletId();
    String getPublicKey();
    byte[] getSignedMessage(String message);
    void startSigningMessage(String message, String desc, IWalletByteResultCallback callback);
    void airDrop(String desc, IWalletByteResultCallback callback);
    void startSigningMessageASCII(String message, String desc, boolean showASCII, IWalletByteResultCallback callback);
}