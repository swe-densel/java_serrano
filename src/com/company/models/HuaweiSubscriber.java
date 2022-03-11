package com.company.models;

public class HuaweiSubscriber extends Subscriber{
    public HuaweiSubscriber(Company company, String msisdn) {
        this.company = company;
        this.msisdn = msisdn;
        this.company.addSubscriber(this);
    }

    @Override
    public void receiveSMS() {
        smsView.showResult("\n" + this.getClass().getName() +
                " \nTIMESTAMP: " + company.getSms().getFormattedTimestamp() +
                " \nTO: " + this.getMsisdn() +
                " \nFROM: " + company.getSms().getShortCode() + " (" +
                company.getSms().getSender() + ") " +
                " \nMESSAGE: " + company.getSms().getPayload()
                + "\n");
    }
}
