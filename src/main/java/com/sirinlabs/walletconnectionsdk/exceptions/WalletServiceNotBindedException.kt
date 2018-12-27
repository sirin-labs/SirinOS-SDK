package com.sirinlabs.walletconnectionsdk.exceptions

const val WALLET_SERVICE_NOT_BINDED = "Wallet Service Not binded, assign callback or use force bind after disconnection"

class WalletServiceNotBindedException : Exception(WALLET_SERVICE_NOT_BINDED)