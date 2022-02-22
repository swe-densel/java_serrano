package com.company.utilities;

import com.company.enums.DefinedPromo;
import com.company.enums.StatusTags;
import com.company.models.Promo;
import com.company.models.SMS;
import com.company.interfaces.SMSManager;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;

//this class is responsible for all of the database operations in this project
public class DatabaseConnect implements SMSManager {
    final private static Logger logger = Logger.getLogger(DatabaseConnect.class.getName());

    private static DatabaseConnect instance = null;
    private static Connection connection = null;
    private Statement statement = null;
    private ResultSet resultSet = null;
    private String query;
    private SMS sms = null;

    //Singleton Database Class / automatically establishes connection when called
    public static DatabaseConnect getInstance(){
        if (instance == null){
            instance = new DatabaseConnect();
        }
        connect();
        return instance;
    }


    //establishes the connection
    private static void connect() {
        try {
            Properties properties = getProperties();

            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection(
                    properties.getProperty("db.url"),
                    properties.getProperty("db.user"),
                    properties.getProperty("db.password"));
            logger.info("DB STATUS: CONNECTED");

        } catch (Exception e){
           logger.log(Level.SEVERE, "Not Connected: ", e);
        }
    }

    //disconnects the db connection after doing any CRUD operation
    public void disconnect(){
        try{
            if (connection!= null){
                connection.close();
                logger.info("DB STATUS: DISCONNECTED");
            }
        } catch (Exception e){
            logger.log(Level.SEVERE, "Not Connected: ", e);
        }
    }

    //gets parameters required for db connection from the config.properties file
    private static Properties getProperties(){
        Properties properties = new Properties();
        try {
            properties.load(
                    new FileInputStream(
                            "/Users/denselross.serrano/IdeaProjects" +
                                    "/Lab2-Serrano/src/com/company" +
                                    "/resources/config.properties")
            );

        } catch (IOException exception){
            logger.log(Level.SEVERE, "IOException: ", exception);
        }
        return properties;
    }

    //inserts promo to the database
    public String insertPromo(Promo promo){
        query = "INSERT INTO sms_db.tbl_promo " +
                "(promoCode, details, shortCode, startDate, endDate) " +
                "values (" +
                "'" + promo.getPromoCode() + "', " +
                "'" + promo.getDetails() + "', " +
                "'" + promo.getShortCode() + "', " +
                "'" + promo.getStartDate() + "', " +
                "'" + promo.getEndDate() + "' )";

        try {
            statement = connection.createStatement();
            statement.execute(query);

        } catch (SQLException e){
            logger.log(Level.SEVERE, "SQLException: ", e);
        } finally {
            try {
                if (statement != null){
                    statement.close();
                }

            } catch (Exception e){
                logger.log(Level.SEVERE, "ERROR IN CLOSING: ", e);
            }
        }

        logger.log(Level.INFO, "INSERTED DATA: " + promo.toString());
        disconnect();
        return "Created Promo: " + promo.getDetails();
    }

    //inserts SMS to the database
    @Override
    public void insertSMS(SMS sms) {
        query = "INSERT INTO sms_db.tbl_sms " +
                "(msisdn, recipient, sender, shortCode, " +
                "payload, promo, status, timestamp) " +
                "values ( " +

                "'" + sms.getMsisdn() + "', " +
                "'" + sms.getRecipient() + "', " +
                "'" + sms.getSender() + "', " +
                "'" + sms.getShortCode() + "', " +
                "'" + sms.getPayload() + "', " +
                "'" + sms.getDefinedPromoTag().getPromoCode() + "', " +
                "'" + sms.getStatusTag().name() + "', " +
                "'" + sms.getFormattedTimestamp() + "' )";

        try {
            statement = connection.createStatement();
            statement.execute(query);

        } catch (SQLException e){
            logger.log(Level.SEVERE, "SQLException: ", e);
        } finally {
            try {
                if (statement != null){
                    statement.close();
                }

            } catch (Exception e){
                logger.log(Level.SEVERE, "ERROR IN CLOSING: ", e);
            }
        }

        logger.log(Level.INFO, "INSERTED DATA: " + sms.toString());
        disconnect();
    }

    //retrieves SMS list given start date and end date
    @Override
    public ArrayList<SMS> retrieveSMSByDate(String startDate, String endDate) {
        ArrayList<SMS> smsList = new ArrayList<>();

        query = "SELECT * FROM sms_db.tbl_sms " +
                "WHERE timestamp BETWEEN '" + startDate +
                "' AND '" + endDate + "' ";

        return retrieveSMSData(smsList, query);
    }

    //retrieves SMS list given a msisdn
    @Override
    public ArrayList<SMS> retrieveSMSByMsisdn(String msisdn) {
        ArrayList<SMS> smsList = new ArrayList<>();

        query = "SELECT * FROM sms_db.tbl_sms " +
                "WHERE msisdn = '" + msisdn + "' ";

        return retrieveSMSData(smsList, query);
    }

    //retrieves SMS list given multiple msisdn
    @Override
    public ArrayList<SMS> retrieveSMSByMsisdnList(String[] msisdnList) {
        ArrayList<SMS> smsList = new ArrayList<>();

        for (String msisdn : msisdnList){
            query = "SELECT * FROM sms_db.tbl_sms " +
                    "WHERE msisdn = '" + msisdn + "' ";

            try {
                statement = connection.createStatement();
                resultSet = statement.executeQuery(query);

                while (resultSet.next()){
                    sms = new SMS(
                            resultSet.getString("msisdn"),
                            resultSet.getString("recipient"),
                            resultSet.getString("sender"),
                            resultSet.getString("shortCode"),
                            resultSet.getString("transactionID"),
                            resultSet.getString("payload"),
                            LocalDateTime.parse(
                                    resultSet.getString("timestamp"),
                                    DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")),
                            StatusTags.valueOf(resultSet.getString("status")),
                            DefinedPromo.valueOf(resultSet.getString("promo"))
                    );
                    smsList.add(sms);

                    logger.log(Level.INFO, sms.toString());
                }

            } catch (SQLException e) {
                logger.log(Level.SEVERE, "SQLException: ", e);

            } finally {
                try {
                    if (statement != null){
                        statement.close();
                    }
                    if (resultSet != null){
                        resultSet.close();
                    }
                } catch (Exception e){
                    logger.log(Level.SEVERE, "ERROR IN CLOSING: ", e);
                }
            }
            logger.log(Level.INFO, "Retrieved : {0}", smsList);

        }

        disconnect();
        return smsList;
    }

    //retrieves SMS list given a promo code
    @Override
    public ArrayList<SMS> retrieveSMSbyPromoCode(String promoCode) {
        ArrayList<SMS> smsList = new ArrayList<>();

        query = "SELECT * FROM sms_db.tbl_sms AS s " +
                "INNER JOIN sms_db.tbl_promo AS p " +
                "ON s.shortCode = p.shortCode " +
                "WHERE p.promoCode = '" + promoCode + "' ";

        return retrieveSMSData(smsList, query);
    }

    //retrieves SMS list sent by the system
    @Override
    public ArrayList<SMS> retrieveSMSfromSystem(String sender) {
        ArrayList<SMS> smsList = new ArrayList<>();

        query = "SELECT * FROM sms_db.tbl_sms " +
                "WHERE sender = '" + sender + "' ";

        return retrieveSMSData(smsList, query);
    }

    //retrieves SMS list received by the system
    @Override
    public ArrayList<SMS> retrieveSMSToSystem(String recipient) {
        ArrayList<SMS> smsList = new ArrayList<>();

        query = "SELECT * FROM sms_db.tbl_sms " +
                "WHERE recipient = '" + recipient + "' ";

        return retrieveSMSData(smsList, query);
    }

    //improvised function that updates the transaction ID of the
    // recent added SMS because varchar fields cannot be autogenerated
    public void updateTransactionID(Map<String, Object> transactionId){
        Integer id = (Integer) transactionId.get("id");
        String promo = (String) transactionId.get("promo");

        //transaction ID = promo + autogenerated id
        query = "UPDATE sms_db.tbl_sms " +
                "SET transactionID = '" + promo + id + "' " +
                "WHERE id = " + id;

        try {
            statement = connection.createStatement();
            statement.execute(query);

        } catch (SQLException e){
            logger.log(Level.SEVERE, "SQLException: ", e);
        } finally {
            try {
                if (statement != null){
                    statement.close();
                }

            } catch (Exception e){
                logger.log(Level.SEVERE, "ERROR IN CLOSING: ", e);
            }
        }

        logger.log(Level.INFO, "UPDATED DATA: TransactionID = " + promo + id);
        disconnect();
    }

    //gets the id and promo of the recent added SMS
    //used for supplying the parameters required by the updateTransactionID
    public Map<String, Object> getIdPromo() {
        query = "SELECT s.id, s.promo " +
                "FROM sms_db.tbl_sms AS s " +
                "ORDER BY s.id DESC ";
        Map<String, Object> idPromo = new HashMap<>();

        try {
            statement = connection.createStatement();
            resultSet = statement.executeQuery(query);
            resultSet.next();

            idPromo.put("id", resultSet.getInt("id"));
            idPromo.put("promo", resultSet.getString("promo"));

            logger.log(Level.INFO, "READ DATA: " + idPromo.toString());

        } catch (SQLException e) {
            logger.log(Level.SEVERE, "SQLException: ", e);
        } finally {
            try {
                if (statement != null){
                    statement.close();
                }

            } catch (Exception e){
                logger.log(Level.SEVERE, "ERROR IN CLOSING: ", e);
            }
        }

        disconnect();
        return idPromo;
    }

    //main shared function for retrieving SMS transactions
    private ArrayList<SMS> retrieveSMSData(ArrayList<SMS> smsList, String query) {
        try {
            statement = connection.createStatement();
            resultSet = statement.executeQuery(query);

            while (resultSet.next()){
                sms = new SMS(
                        resultSet.getString("msisdn"),
                        resultSet.getString("recipient"),
                        resultSet.getString("sender"),
                        resultSet.getString("shortCode"),
                        resultSet.getString("transactionID"),
                        resultSet.getString("payload"),
                        LocalDateTime.parse(
                                resultSet.getString("timestamp"),
                                DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")),
                        StatusTags.valueOf(resultSet.getString("status")),
                        DefinedPromo.valueOf(resultSet.getString("promo"))
                );
                smsList.add(sms);

                logger.log(Level.INFO, sms.toString());
            }

        } catch (SQLException e) {
            logger.log(Level.SEVERE, "SQLException: ", e);

        } finally {
            try {
                if (statement != null){
                    statement.close();
                }
                if (resultSet != null){
                    resultSet.close();
                }
            } catch (Exception e){
                logger.log(Level.SEVERE, "ERROR IN CLOSING: ", e);
            }
        }
        logger.log(Level.INFO, "Retrieved : {0}", smsList);

        disconnect();
        return smsList;
    }
}
