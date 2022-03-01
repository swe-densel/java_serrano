package com.company.models;

import com.company.enums.DefinedPromo;
import com.company.enums.StatusTags;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

//POJO for SMS
public class SMS {
    private String msisdn;
    private String recipient;
    private String sender;
    private String shortCode;
    private String transactionID;
    private String payload;
    private LocalDateTime timestamp;
    private StatusTags statusTag;
    private DefinedPromo definedPromoTag;

    //this constructor is used for creating SMS sent by the user
    public SMS(String msisdn,
               String recipient,
               String sender,
               String shortCode,
               String transactionID,
               String payload,
               LocalDateTime timestamp) {

        this.msisdn = msisdn;
        this.recipient = recipient;
        this.sender = sender;
        this.shortCode = shortCode;
        this.transactionID = transactionID;
        this.payload = payload;
        this.timestamp = timestamp;
    }

    //this constructor is used for creating SMS sent by the system
    public SMS(String msisdn,
               String recipient,
               String sender,
               String shortCode,
               String transactionID,
               String payload,
               LocalDateTime timestamp,
               StatusTags statusTag,
               DefinedPromo definedPromoTag) {

        this.msisdn = msisdn;
        this.recipient = recipient;
        this.sender = sender;
        this.shortCode = shortCode;
        this.transactionID = transactionID;
        this.payload = payload;
        this.timestamp = timestamp;
        this.statusTag = statusTag;
        this.definedPromoTag = definedPromoTag;
    }

    public String getMsisdn() {
        return msisdn;
    }

    public void setMsisdn(String msisdn) {
        this.msisdn = msisdn;
    }

    public String getRecipient() {
        return recipient;
    }

    public void setRecipient(String recipient) {
        this.recipient = recipient;
    }

    public String getSender() {
        return sender;
    }

    public void setSender(String sender) {
        this.sender = sender;
    }

    public String getShortCode() {
        return shortCode;
    }

    public void setShortCode(String shortCode) {
        this.shortCode = shortCode;
    }

    public String getTransactionID() {
        return transactionID;
    }

    public void setTransactionID(String transactionID) {
        this.transactionID = transactionID;
    }

    public String getPayload() {
        return payload;
    }

    public void setPayload(String payload) {
        this.payload = payload;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(LocalDateTime timestamp) {
        this.timestamp = timestamp;
    }

    public String getFormattedTimestamp(){
        return this.timestamp.format(
                DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
    }

    public StatusTags getStatusTag() {
        return statusTag;
    }

    public void setStatusTag(StatusTags statusTag) {
        this.statusTag = statusTag;
    }

    public DefinedPromo getDefinedPromoTag() {
        return definedPromoTag;
    }

    public void setDefinedPromoTag(DefinedPromo definedPromoTag) {
        this.definedPromoTag = definedPromoTag;
    }

    //creates a Map with msisdn, message, and shortcode as its items
    public Map<String, String> getSmsMap() {
        Map<String, String> smsMap = new HashMap<>();
        smsMap.put("msisdn", this.getMsisdn());
        smsMap.put("message", this.getPayload());
        smsMap.put("shortCode", this.getShortCode());
        return smsMap;
    }

    //this is the SMS Checker function which accepts a Map and
    //tags which promo does the SMS belong and if the promo is valid
    //based on promo rules

    //map uses String key : String value
    public void checkSMS(Map<String, String> smsMap){

        //determine what promo it belongs, tags promoTag
        this.definedPromoTag =
                getDefinedPromoTag(
                        //.get("key") = gets the value of that key
                        smsMap.get("message"),
                        smsMap.get("shortCode")
                );

        //comparison based on promo rules
        //if the SMS passes all of these conditions, the SMS is tagged as SUCCESS
        if (checkMsisdn(smsMap.get("msisdn"))
                && checkMessage(smsMap.get("message"), this.definedPromoTag)
                && checkDate(this.timestamp, this.definedPromoTag)){
            this.statusTag = StatusTags.SUCCESS_SMS;

            //if the SMS failed at least of the conditions above, the SMS is tagged
            //as FAILED
        } else {
            this.statusTag = StatusTags.FAILED_SMS;
        }

    }

    //checks if the user's msisdn is exactly 13 characters long
    private boolean checkMsisdn(String msisdn){
        return msisdn.length() == 13;
    }

    //checks if the user sent a correct keyword and/or is following instructions
    private boolean checkMessage(String message, DefinedPromo definedPromoTag){
        return definedPromoTag.getPromoCode().equals(message)
                || message.equals("REGISTER")
                || message.contains(", "); //format of "Lastname, Firstname"
    }

    //checks if the user sent date and time is within the promo period
    private boolean checkDate(LocalDateTime timestamp, DefinedPromo definedPromo){
        return definedPromo.getStartDate().isBefore(timestamp)
                && definedPromo.getEndDate().isAfter(timestamp);
    }

    //determines the promo of the SMS based on the payload/message sent by the user
    // and the shortcode (Assumption: each promo has a different shortcode)
    private DefinedPromo getDefinedPromoTag(String message, String shortCode){
        DefinedPromo promo = null;

        for (DefinedPromo definedPromo : DefinedPromo.values()){
            //if statement condition: if the message of SMS is equal to the promo and
            // shortcode of SMS is equal to that of the promo
            if (definedPromo.getPromoCode().equals(message)
                    || definedPromo.getShortCode().equals(shortCode)){
                promo = definedPromo;
            }
        }

        return promo;
    }

    //used to display the SMS transaction
    @Override
    public String toString() {
        return "SMS{" +
                "msisdn='" + msisdn + '\'' +
                ", recipient='" + recipient + '\'' +
                ", sender='" + sender + '\'' +
                ", shortCode='" + shortCode + '\'' +
                ", transactionID='" + transactionID + '\'' +
                ", payload='" + payload + '\'' +
                ", timestamp=" + timestamp +
                ", statusTag=" + statusTag.name() +
                ", definedPromoTag=" + definedPromoTag.getPromoCode() +
                '}';
    }

    private SMS(SMS SMSBuilder){
        this.msisdn = SMSBuilder.msisdn;
        this.recipient = SMSBuilder.recipient;
        this.sender = SMSBuilder.sender;
        this.shortCode = SMSBuilder.shortCode;
        this.transactionID = SMSBuilder.transactionID;
        this.payload = SMSBuilder.payload;
        this.timestamp = SMSBuilder.timestamp;
    }

    public static class SMSBuilder{
        private String msisdn;
        private String recipient;
        private String sender;
        private String shortCode;
        private String transactionID;
        private String payload;
        private LocalDateTime timestamp;

        public SMSBuilder(String msisdn, String shortCode, String payload) {
            this.msisdn = msisdn;
            this.shortCode = shortCode;
            this.payload = payload;
        }

        public SMSBuilder(SMSBuilder smsBuilder) {
        }

        public SMSBuilder addRecipient(String recipient){
            this.recipient = recipient;
            return this;
        }

        public SMSBuilder addSender(String sender){
            this.sender = sender;
            return this;
        }

        public SMSBuilder addtransactionID(String transactionID){
            this.transactionID = transactionID;
            return this;
        }

        public SMSBuilder build(){
            SMSBuilder smsbuilder = new SMSBuilder(this);
            return smsbuilder;
        }
    }
    //  -------- BUILDER CLASS -----------

    // 1. Create a public static class named "SMSBuilder" here
    // 2. Copy the attributes of the SMS class and paste it inside the SMSBuilder class
    //    Do not include DefinedPromo and StatusTags
    // 3. Generate a constructor for the SMSBuilder with msisdn, shortCode, and payload as its parameters
    //    (Right click > Constructor > Hold CMD and select msisdn, shortCode, and payload > Click OK)

    // 4. Create SMSBuilder methods for each attribute except msisdn, recipient, shortCode, DefinedPromo, and StatusTags
    // example:

    // public SMSBuilder addTransactionID(String transactionID) {
    //            this.transactionID = transactionID;
    //            return this;
    //        }

    // 5. Create an SMS build() method. You can use the example below and just replace the Form with SMS
    // example:

    //    public Form build(){
    //        Form form = new Form(this);
    //        return form;
    //    }

    // 6. Create a new constructor in the SMS class with SMSBuilder as its parameters.
    // Do not include DefinedPromo and StatusTags
    // Use the example below as a guide.

    //    private Form(FormBuilder builder) {
    //        this.firstName = builder.firstName;
    //        this.middleName = builder.middleName;
    //        this.lastName = builder.lastName;
    //        this.address = builder.address;
    //        this.birthdate = builder.birthdate;
    //        this.spouseFirstName = builder.spouseFirstName;
    //        this.spouseLastName = builder.spouseLastName;
    //        this.spouseMiddleName = builder.spouseMiddleName;
    //    }

    // 7. Go to the Main class for the next step

}
