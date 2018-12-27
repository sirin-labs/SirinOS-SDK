/*
 * code:<br />Copyright (C) 2018 SIRIN LABS AG
 */

package exportedmethods;

oneway interface IWalletByteResultCallback {

    void success(in byte[] result);
    void failure(String errorMessage);

}