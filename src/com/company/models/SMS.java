package com.company.models;

import com.company.enums.DefinedPromo;
import com.company.enums.StatusTags;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;

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

    private SMS(SMSBuilder smsbuilder){
        this.msisdn = smsbuilder.msisdn;
        this.recipient = smsbuilder.recipient;
        this.sender = smsbuilder.sender;
        this.shortCode = smsbuilder.shortCode;
        this.transactionID = smsbuilder.transactionID;
        this.payload = smsbuilder.payload;
        this.timestamp = smsbuilder.timestamp;
    }

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

    //Builder class for SMS
    public static class SMSBuilder{
        private String recipient;
        private String sender;
        private String msisdn;
        private String shortCode;
        private String transactionID;
        private String payload;
        private LocalDateTime timestamp;

        public SMSBuilder(String shortCode, String payload) {
            this.shortCode = shortCode;
            this.payload = payload;
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

        public SMSBuilder addMsisdn(String msisdn){
            this.msisdn = msisdn;
            return this;
        }

        public SMSBuilder addTimestamp(LocalDateTime timestamp){
            this.timestamp = timestamp;
            return this;
        }

        //creates and returns an SMS object
        public SMS build(){
            SMS sms = new SMS(this);
            return sms;
        }
    }
}
