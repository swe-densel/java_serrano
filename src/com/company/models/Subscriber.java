package com.company.models;

import com.company.views.SMSView;

//Serves as the Observer / Consumer
public abstract class Subscriber {
    protected Company company;
    protected String msisdn;
    protected SMSView smsView = new SMSView();
    public abstract void receiveSMS();

    public String getMsisdn() {
        return msisdn;
    }
}
