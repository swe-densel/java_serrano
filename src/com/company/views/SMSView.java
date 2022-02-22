package com.company.views;

import com.company.models.SMS;

import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

//this class is responsible for logging out to the user and generating the report file
public class SMSView {
    final private static Logger logger = Logger.getLogger(SMSView.class.getName());

    public void showResult(String message){
        logger.info("VIEW: " + message);
    }

    public void showResult(String message, Logger logger){
        logger.info("VIEW: " + message);
    }

    public void showSMSResult(ArrayList<SMS> smsList, Logger logger){

        logger.log(Level.INFO, "------------");
        for (SMS sms : smsList){
            logger.info("VIEW: " + sms.toString());
        }
        logger.log(Level.INFO, "------------");

    }

    public void showPersonsResult(ArrayList<String> persons, Logger logger){
        logger.log(Level.INFO, "------------");
        for (String person : persons){
            logger.info("VIEW: " + person);
        }
        logger.log(Level.INFO, "------------");
    }
}
