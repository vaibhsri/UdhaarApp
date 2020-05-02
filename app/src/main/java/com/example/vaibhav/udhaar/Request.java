package com.example.vaibhav.udhaar;

/**
 * Created by Vaibhav on 11/19/2018.
 */

public class Request {

    private String Amount;
    private String Interest;
    private String Phone;

    public Request() {
    }

    public Request(String Amount, String Interest, String Phone) {
        this.Amount = Amount;
        this.Interest = Interest;
        this.Phone = Phone;
    }

    public String getAmount() {
        return Amount;
    }

    public void setAmount(String Amount) {
        this.Amount = Amount;
    }

    public String getInterest() {
        return Interest;
    }

    public void setInterest(String Interest) {
        this.Interest = Interest;
    }

    public String getPhone(){
        return Phone;
    }

    public void setPhone(String Phone){
        this.Phone = Phone;
    }
}
