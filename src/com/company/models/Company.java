package com.company.models;

import java.util.ArrayList;
import java.util.List;

//Serves as the Observable / Producer
public class Company {
    private List<Subscriber> subscribers = new ArrayList<>();
    private SMS sms;

    public SMS getSms() {
        return sms;
    }

    //serves as the update function of Observer design pattern
    public void sendSMS(SMS sms) {
        this.sms = sms;
        notifyAllSubscribers();
    }

    //Attaches subscribers into the List of Subscribers to notify
    public void addSubscriber(Subscriber subscriber){
        subscribers.add(subscriber);
    }

    //Notifies each subscriber by calling their respective receiveSMS() function
    public void notifyAllSubscribers(){
        for (Subscriber subscriber : subscribers){
            subscriber.receiveSMS();
        }
    }
}
