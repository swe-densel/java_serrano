package com.company.interfaces;

import com.company.models.SMS;

import java.util.ArrayList;

//Interface with the main functions for Report Generator
public interface ReportManager {
    ArrayList<SMS> retrieveFailedTransactions(ArrayList<SMS> smsList);
    ArrayList<SMS> retrieveSuccessfulTransactions(ArrayList<SMS> smsList);
    ArrayList<String> retrievePersons(ArrayList<SMS> smsList);
    int getTotalSMSReceived(ArrayList<SMS> smsList);
    int getTotalSMSSent(ArrayList<SMS> smsList);
}
