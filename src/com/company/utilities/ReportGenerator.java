package com.company.utilities;

import com.company.enums.DefinedPromo;
import com.company.enums.StatusTags;
import com.company.interfaces.ReportManager;
import com.company.models.SMS;
import java.util.ArrayList;

//this class is used to filter out and retrieve SMS transactions
//based on what is required for the report
public class ReportGenerator implements ReportManager {
    private static ReportGenerator instance = null;

    ArrayList<SMS> retrievedList = new ArrayList<>();
    ArrayList<String> persons = new ArrayList<>();

    public static ReportGenerator getInstance(){
        if (instance == null){
            instance = new ReportGenerator();
        }
        return instance;
    }

    //returns only failed transactions
    //the parameters of this function are the Arraylist<SMS> results from
    //DatabaseConnect overridden functions
    @Override
    public ArrayList<SMS> retrieveFailedTransactions(ArrayList<SMS> smsList) {
        for (SMS sms : smsList){
            if (sms.getStatusTag() == StatusTags.FAILED_SMS
                    && sms.getDefinedPromoTag() == DefinedPromo.PROMO){
                retrievedList.add(sms);
            }
        }
        return retrievedList;
    }

    //returns only successful transactions
    //the parameters of this function are the Arraylist<SMS> results from
    //DatabaseConnect overridden functions
    @Override
    public ArrayList<SMS> retrieveSuccessfulTransactions(ArrayList<SMS> smsList) {
        for (SMS sms : smsList){
            if (sms.getStatusTag() == StatusTags.SUCCESS_SMS
                    && sms.getDefinedPromoTag() == DefinedPromo.PROMO){
                retrievedList.add(sms);
            }
        }
        return retrievedList;
    }

    //returns list of persons who joined the promo
    @Override
    public ArrayList<String> retrievePersons(ArrayList<SMS> smsList) {
        for (SMS sms : smsList){
            if (sms.getStatusTag() == StatusTags.SUCCESS_SMS
                    && sms.getDefinedPromoTag() == DefinedPromo.PROMO
                    && sms.getPayload().contains(", ")
                    && !sms.getSender().equals(DefinedPromo.PROMO.getCompany())){
                persons.add(sms.getPayload());
            }
        }
        return persons;
    }

    //returns total count of SMS received
    @Override
    public int getTotalSMSReceived(ArrayList<SMS> smsList) {
        int total = 0;

        for (SMS sms : smsList){
            if (sms.getDefinedPromoTag() == DefinedPromo.PROMO){
                total++;
            }
        }
        return total;
    }

    //returns total count of SMS sent
    @Override
    public int getTotalSMSSent(ArrayList<SMS> smsList) {
        int total = 0;

        for (SMS sms : smsList){
            if (sms.getDefinedPromoTag() == DefinedPromo.PROMO){
                total++;
            }
        }
        return total;
    }
}
