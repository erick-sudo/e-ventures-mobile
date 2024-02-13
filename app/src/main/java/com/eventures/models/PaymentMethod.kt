package com.eventures.models

enum class PaymentMethod(
    private var method: String
) {
    Cash("Cash"),
    Cheque("Cheque"),
    OnlineTransfer("OnlineTransfer"),
    CreditCard("CreditCard"),
    DebitCard("DebitCard")
}