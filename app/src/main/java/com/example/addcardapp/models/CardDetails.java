package com.example.addcardapp.models;

public class CardDetails {
    private String balance;
    private String expiry;
    private String cardNumber;
    private String cardType;


    public CardDetails(String balance, String expiry, String cardNumber, String cardType) {
        this.balance = balance;
        this.expiry = expiry;
        this.cardNumber = cardNumber;
        this.cardType = cardType;
    }

    public String getBalance() {
        return balance;
    }

    public void setBalance(String balance) {
        this.balance = balance;
    }

    public String getExpiry() {
        return expiry;
    }

    public void setExpiry(String expiry) {
        this.expiry = expiry;
    }

    public String getCardNumber() {
        return cardNumber;
    }

    public void setCardNumber(String cardNumber) {
        this.cardNumber = cardNumber;
    }

    public String getCardType() {
        return cardType;
    }

    public void setCardType(String cardType) {
        this.cardType = cardType;
    }
}
