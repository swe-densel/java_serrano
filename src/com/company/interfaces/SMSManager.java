package com.company.interfaces;

import com.company.models.SMS;

import java.util.ArrayList;

//Interface with the main functions for Database Connect
public interface SMSManager {
    void insertSMS(SMS sms);
    ArrayList<SMS> retrieveSMSByDate(String startDate, String endDate);
    ArrayList<SMS> retrieveSMSByMsisdn(String msisdn);
    ArrayList<SMS> retrieveSMSByMsisdnList(String[] msisdnList);
    ArrayList<SMS> retrieveSMSbyPromoCode(String promoCode);
    ArrayList<SMS> retrieveSMSfromSystem(String sender);
    ArrayList<SMS> retrieveSMSToSystem(String recipient);
}
