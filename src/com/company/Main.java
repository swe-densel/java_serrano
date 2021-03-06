package com.company;

import com.company.controllers.SMSController;
import com.company.enums.DefinedPromo;
import com.company.models.*;

import java.time.LocalDateTime;

public class Main {
    public static void main(String[] args) {
        SMS sms = buildSMS();
        notifySubscribers(sms);
    }

    //This method builds the SMS using Builder class and returns the SMSBuilder
    private static SMS buildSMS() {

        //DefinedPromo enum is used here to supply the parameters required for the SMS
        SMS.SMSBuilder smsBuilder = new SMS.SMSBuilder(
                DefinedPromo.PROMO.getShortCode(),
                DefinedPromo.PROMO.getDetails()
        );
        smsBuilder.addSender(DefinedPromo.PROMO.getCompany());
        smsBuilder.addTimestamp(LocalDateTime.now());
        return smsBuilder.build();
    }

    //This method sends an SMS to notify all subscribers using Observer design pattern
    private static void notifySubscribers(SMS sms){
        Company company = new Company();

        new SamsungSubscriber(company, "+639771579234");
        new HuaweiSubscriber(company, "+639774316258");

        company.sendSMS(sms);
    }

    private static void generateReport() {
        //----Report Generation 4th part-----
        SMSController.getInstance().generateReport();
    }

    private static void populateData() {
        //DATA POPULATION 3rd Part-----------------------

        SMSController.getInstance()
                .sendSMS(new SMS(
                        "+639563026795",
                        "Pizza Place",
                        "Juan dela Cruz",
                        "1234555",
                        null,
                        "PRAMO",
                        LocalDateTime.of(
                                2021,
                                03,
                                02,
                                11,
                                0))
                );

        SMSController.getInstance()
                .sendSMS(new SMS(
                        "+639563026795",
                        "Pizza Place",
                        "Juan dela Cruz",
                        "1234555",
                        null,
                        "promo",
                        LocalDateTime.of(
                                2021,
                                03,
                                02,
                                11,
                                0))
                );

        SMSController.getInstance()
                .sendSMS(new SMS(
                        "+639563026795",
                        "Pizza Place",
                        "Juan dela Cruz",
                        "1234555",
                        null,
                        "Pr0m0",
                        LocalDateTime.of(
                                2021,
                                03,
                                02,
                                11,
                                0))
                );

        SMSController.getInstance()
                .sendSMS(new SMS(
                        "+639563026795",
                        "Pizza Place",
                        "Juan dela Cruz",
                        "1234555",
                        null,
                        "register",
                        LocalDateTime.of(
                                2021,
                                03,
                                02,
                                11,
                                0))
                );

        SMSController.getInstance()
                .sendSMS(new SMS(
                        "+639563026795",
                        "Pizza Place",
                        "Juan dela Cruz",
                        "1234555",
                        null,
                        "Juan dela Cruz",
                        LocalDateTime.of(
                                2021,
                                03,
                                02,
                                11,
                                0))
                );

        SMSController.getInstance()
                .sendSMS(new SMS(
                        "+639563026795",
                        "Pizza Place",
                        "Juan dela Cruz",
                        "1234555",
                        null,
                        "Juan, dela Cruz",
                        LocalDateTime.of(
                                2021,
                                03,
                                02,
                                11,
                                0))
                );

        SMSController.getInstance()
                .sendSMS(new SMS(
                        "+639771425863",
                        "Pizza Place",
                        "Mang Thomas",
                        "1234555",
                        null,
                        "PROMO",
                        LocalDateTime.of(
                                2021,
                                03,
                                02,
                                11,
                                0))
                );

        SMSController.getInstance()
                .sendSMS(new SMS(
                        "+639771425863",
                        "Pizza Place",
                        "Mang Thomas",
                        "1234555",
                        null,
                        "REGISTER",
                        LocalDateTime.of(
                                2021,
                                03,
                                02,
                                11,
                                0))
                );

        SMSController.getInstance()
                .sendSMS(new SMS(
                        "+639771425863",
                        "Pizza Place",
                        "Mang Thomas",
                        "1234555",
                        null,
                        "Thomas, Mang",
                        LocalDateTime.of(
                                2021,
                                03,
                                02,
                                11,
                                0))
                );

        SMSController.getInstance()
                .sendSMS(new SMS(
                        "+639563026795",
                        "Pizza Place",
                        "Juan dela Cruz",
                        "1234555",
                        null,
                        "PROMO",
                        LocalDateTime.of(
                                2020,
                                03,
                                02,
                                11,
                                0))
                );

        SMSController.getInstance()
                .sendSMS(new SMS(
                        "+639563026795",
                        "Pizza Place",
                        "Juan dela Cruz",
                        "1234555",
                        null,
                        "REGISTER",
                        LocalDateTime.of(
                                2022,
                                03,
                                02,
                                11,
                                0))
                );

        SMSController.getInstance()
                .sendSMS(new SMS(
                        "+639563026795",
                        "Pizza Place",
                        "Juan dela Cruz",
                        "1234555",
                        null,
                        "dela Cruz, Juan",
                        LocalDateTime.of(
                                2021,
                                04,
                                03,
                                12,
                                1))
                );

        SMSController.getInstance()
                .sendSMS(new SMS(
                        "+639171582496",
                        "Pizza Place",
                        "Siao Tan",
                        "1234555",
                        null,
                        "PROMO",
                        LocalDateTime.of(
                                2021,
                                03,
                                02,
                                13,
                                0))
                );

        SMSController.getInstance()
                .sendSMS(new SMS(
                        "+639171582496",
                        "Pizza Place",
                        "Siao Tan",
                        "1234555",
                        null,
                        "REGISTER",
                        LocalDateTime.of(
                                2021,
                                03,
                                02,
                                14,
                                0))
                );

        SMSController.getInstance()
                .sendSMS(new SMS(
                        "+639171582496",
                        "Pizza Place",
                        "Siao Tan",
                        "1234555",
                        null,
                        "Tan, Siao",
                        LocalDateTime.of(
                                2021,
                                03,
                                02,
                                15,
                                0))
                );

        SMSController.getInstance()
                .sendSMS(new SMS(
                        "+6395630267",
                        "Pizza Place",
                        "Juan dela Cruz",
                        "1234555",
                        null,
                        "PROMO",
                        LocalDateTime.of(
                                2021,
                                03,
                                02,
                                11,
                                0))
                );

        SMSController.getInstance()
                .sendSMS(new SMS(
                        "+6395630267",
                        "Pizza Place",
                        "Juan dela Cruz",
                        "1234555",
                        null,
                        "REGISTER",
                        LocalDateTime.of(
                                2021,
                                03,
                                02,
                                11,
                                0))
                );

        SMSController.getInstance()
                .sendSMS(new SMS(
                        "+6395630267",
                        "Pizza Place",
                        "Juan dela Cruz",
                        "1234555",
                        null,
                        "dela Cruz, Juan",
                        LocalDateTime.of(
                                2021,
                                03,
                                02,
                                11,
                                0))
                );

        SMSController.getInstance()
                .sendSMS(new SMS(
                        "+639158761953",
                        "Pizza Place",
                        "Miguel Aveno",
                        "1234555",
                        null,
                        "PROMO",
                        LocalDateTime.of(
                                2021,
                                05,
                                20,
                                12,
                                1))
                );

        SMSController.getInstance()
                .sendSMS(new SMS(
                        "+639158761953",
                        "Pizza Place",
                        "Miguel Aveno",
                        "1234555",
                        null,
                        "REGISTER",
                        LocalDateTime.of(
                                2021,
                                05,
                                20,
                                12,
                                15))
                );

        SMSController.getInstance()
                .sendSMS(new SMS(
                        "+639158761953",
                        "Pizza Place",
                        "Miguel Aveno",
                        "1234555",
                        null,
                        "Aveno, Miguel",
                        LocalDateTime.of(
                                2021,
                                05,
                                20,
                                12,
                                15))
                );

        SMSController.getInstance()
                .sendSMS(new SMS(
                        "+639158761953",
                        "Pizza Place",
                        "Miguel Aveno",
                        "1234555",
                        null,
                        "Aveno Miguel",
                        LocalDateTime.of(
                                2021,
                                05,
                                20,
                                12,
                                15))
                );

        SMSController.getInstance()
                .sendSMS(new SMS(
                        "+639775183692",
                        "Pizza Place",
                        "Boris Ivanovich",
                        "1234555",
                        null,
                        "PR0M0",
                        LocalDateTime.of(
                                2022,
                                10,
                                10,
                                16,
                                30))
                );

        SMSController.getInstance()
                .sendSMS(new SMS(
                        "+639775183692",
                        "Pizza Place",
                        "Boris Ivanovich",
                        "1234555",
                        null,
                        "register",
                        LocalDateTime.of(
                                2022,
                                10,
                                10,
                                16,
                                40))
                );

        SMSController.getInstance()
                .sendSMS(new SMS(
                        "+639775183692",
                        "Pizza Place",
                        "Boris Ivanovich",
                        "1234555",
                        null,
                        "PR0M0",
                        LocalDateTime.of(
                                2021,
                                04,
                                26,
                                14,
                                45))
                );

        SMSController.getInstance()
                .sendSMS(new SMS(
                        "+639775183692",
                        "Pizza Place",
                        "Boris Ivanovich",
                        "1234555",
                        null,
                        "REGISTER",
                        LocalDateTime.of(
                                2021,
                                04,
                                26,
                                14,
                                50))
                );

        SMSController.getInstance()
                .sendSMS(new SMS(
                        "+639775183692",
                        "Pizza Place",
                        "Boris Ivanovich",
                        "1234555",
                        null,
                        "Ivanovich, Boris",
                        LocalDateTime.of(
                                2021,
                                04,
                                26,
                                15,
                                00))
                );

        SMSController.getInstance()
                .sendSMS(new SMS(
                        "+639563026795",
                        "Tap Station",
                        "Juan dela Cruz",
                        "4551700",
                        null,
                        "HAPPY HOUR",
                        LocalDateTime.of(
                                2022,
                                02,
                                14,
                                20,
                                1))
                );

        SMSController.getInstance()
                .sendSMS(new SMS(
                        "+639563026795",
                        "Tap Station",
                        "Juan dela Cruz",
                        "4551700",
                        null,
                        "register",
                        LocalDateTime.of(
                                2022,
                                02,
                                14,
                                20,
                                2))
                );

        SMSController.getInstance()
                .sendSMS(new SMS(
                        "+639563026795",
                        "Tap Station",
                        "Juan dela Cruz",
                        "4551700",
                        null,
                        "dela Cruz Juan",
                        LocalDateTime.of(
                                2022,
                                02,
                                14,
                                20,
                                3))
                );

        SMSController.getInstance()
                .sendSMS(new SMS(
                        "+639171972483",
                        "Tap Station",
                        "Antonio Lee",
                        "4551700",
                        null,
                        "HAPPYHOUR",
                        LocalDateTime.of(
                                2022,
                                02,
                                14,
                                22,
                                40))
                );

        SMSController.getInstance()
                .sendSMS(new SMS(
                        "+639171972483",
                        "Tap Station",
                        "Antonio Lee",
                        "4551700",
                        null,
                        "REGISTER",
                        LocalDateTime.of(
                                2022,
                                02,
                                14,
                                22,
                                42))
                );

        SMSController.getInstance()
                .sendSMS(new SMS(
                        "+639171972483",
                        "Tap Station",
                        "Antonio Lee",
                        "4551700",
                        null,
                        "Lee, Antonio",
                        LocalDateTime.of(
                                2022,
                                02,
                                14,
                                22,
                                45))
                );

        SMSController.getInstance()
                .sendSMS(new SMS(
                        "+6395630267",
                        "Tap Station",
                        "Juan dela Cruz",
                        "4551700",
                        null,
                        "HAPPYHOUR",
                        LocalDateTime.of(
                                2022,
                                02,
                                14,
                                20,
                                1))
                );

        SMSController.getInstance()
                .sendSMS(new SMS(
                        "+6395630267",
                        "Tap Station",
                        "Juan dela Cruz",
                        "4551700",
                        null,
                        "REGISTER",
                        LocalDateTime.of(
                                2022,
                                02,
                                14,
                                20,
                                2))
                );

        SMSController.getInstance()
                .sendSMS(new SMS(
                        "+6391230267",
                        "Tap Station",
                        "Juan dela Cruz",
                        "4551700",
                        null,
                        "dela Cruz, Juan",
                        LocalDateTime.of(
                                2022,
                                02,
                                14,
                                20,
                                3))
                );

        SMSController.getInstance()
                .sendSMS(new SMS(
                        "+639774937521",
                        "Tap Station",
                        "Boris Ivanovich",
                        "4551700",
                        null,
                        "HAPPYHOUR",
                        LocalDateTime.of(
                                2022,
                                02,
                                14,
                                21,
                                5))
                );

        SMSController.getInstance()
                .sendSMS(new SMS(
                        "+639774937521",
                        "Tap Station",
                        "Boris Ivanovich",
                        "4551700",
                        null,
                        "REGISTER",
                        LocalDateTime.of(
                                2022,
                                02,
                                14,
                                21,
                                10))
                );

        SMSController.getInstance()
                .sendSMS(new SMS(
                        "+639774937521",
                        "Tap Station",
                        "Boris Ivanovich",
                        "4551700",
                        null,
                        "Ivanovich, Boris",
                        LocalDateTime.of(
                                2022,
                                02,
                                14,
                                21,
                                16))
                );

        SMSController.getInstance()
                .sendSMS(new SMS(
                        "+639563026795",
                        "Tap Station",
                        "Juan dela Cruz",
                        "4551700",
                        null,
                        "HAPPYHOUR",
                        LocalDateTime.of(
                                2022,
                                03,
                                15,
                                0,
                                1))
                );

        SMSController.getInstance()
                .sendSMS(new SMS(
                        "+639563026795",
                        "Tap Station",
                        "Juan dela Cruz",
                        "4551700",
                        null,
                        "REGISTER",
                        LocalDateTime.of(
                                2022,
                                03,
                                15,
                                0,
                                3))
                );

        SMSController.getInstance()
                .sendSMS(new SMS(
                        "+639563026795",
                        "Tap Station",
                        "Juan dela Cruz",
                        "4551700",
                        null,
                        "dela Cruz, Juan",
                        LocalDateTime.of(
                                2022,
                                03,
                                15,
                                0,
                                5))
                );

        SMSController.getInstance()
                .sendSMS(new SMS(
                        "+639563026795",
                        "Pasta House",
                        "Juan dela Cruz",
                        "8080224",
                        null,
                        "SPAG 50",
                        LocalDateTime.of(
                                2021,
                                03,
                                02,
                                11,
                                0))
                );

        SMSController.getInstance()
                .sendSMS(new SMS(
                        "+639563026795",
                        "Pasta House",
                        "Juan dela Cruz",
                        "8080224",
                        null,
                        "speg",
                        LocalDateTime.of(
                                2021,
                                03,
                                02,
                                11,
                                0))
                );

        SMSController.getInstance()
                .sendSMS(new SMS(
                        "+639563026795",
                        "Pasta House",
                        "Juan dela Cruz",
                        "8080224",
                        null,
                        "p4st4",
                        LocalDateTime.of(
                                2021,
                                03,
                                02,
                                11,
                                0))
                );

        SMSController.getInstance()
                .sendSMS(new SMS(
                        "+639563026795",
                        "Pasta House",
                        "Juan dela Cruz",
                        "8080224",
                        null,
                        "register",
                        LocalDateTime.of(
                                2021,
                                03,
                                02,
                                11,
                                0))
                );

        SMSController.getInstance()
                .sendSMS(new SMS(
                        "+639563026795",
                        "Pasta House",
                        "Juan dela Cruz",
                        "8080224",
                        null,
                        "Juan dela Cruz",
                        LocalDateTime.of(
                                2021,
                                03,
                                02,
                                11,
                                0))
                );

        SMSController.getInstance()
                .sendSMS(new SMS(
                        "+639563026795",
                        "Pasta House",
                        "Juan dela Cruz",
                        "8080224",
                        null,
                        "Juan, dela Cruz",
                        LocalDateTime.of(
                                2021,
                                03,
                                02,
                                11,
                                0))
                );

        SMSController.getInstance()
                .sendSMS(new SMS(
                        "+639771425863",
                        "Pasta House",
                        "Mang Thomas",
                        "8080224",
                        null,
                        "SPAG50",
                        LocalDateTime.of(
                                2021,
                                03,
                                02,
                                11,
                                0))
                );

        SMSController.getInstance()
                .sendSMS(new SMS(
                        "+639771425863",
                        "Pasta House",
                        "Mang Thomas",
                        "8080224",
                        null,
                        "REGISTER",
                        LocalDateTime.of(
                                2021,
                                03,
                                02,
                                11,
                                0))
                );

        SMSController.getInstance()
                .sendSMS(new SMS(
                        "+639771425863",
                        "Pasta House",
                        "Mang Thomas",
                        "8080224",
                        null,
                        "Thomas, Mang",
                        LocalDateTime.of(
                                2021,
                                03,
                                02,
                                11,
                                0))
                );

        SMSController.getInstance()
                .sendSMS(new SMS(
                        "+639563026795",
                        "Pasta House",
                        "Juan dela Cruz",
                        "8080224",
                        null,
                        "SPAG50",
                        LocalDateTime.of(
                                2020,
                                03,
                                02,
                                11,
                                0))
                );

        SMSController.getInstance()
                .sendSMS(new SMS(
                        "+639563026795",
                        "Pasta House",
                        "Juan dela Cruz",
                        "8080224",
                        null,
                        "REGISTER",
                        LocalDateTime.of(
                                2022,
                                03,
                                02,
                                11,
                                0))
                );

        SMSController.getInstance()
                .sendSMS(new SMS(
                        "+639563026795",
                        "Pasta House",
                        "Juan dela Cruz",
                        "8080224",
                        null,
                        "dela Cruz, Juan",
                        LocalDateTime.of(
                                2021,
                                04,
                                03,
                                12,
                                1))
                );

        SMSController.getInstance()
                .sendSMS(new SMS(
                        "+639171582496",
                        "Pasta House",
                        "Siao Tan",
                        "8080224",
                        null,
                        "SPAG50",
                        LocalDateTime.of(
                                2021,
                                03,
                                02,
                                13,
                                0))
                );

        SMSController.getInstance()
                .sendSMS(new SMS(
                        "+639171582496",
                        "Pasta House",
                        "Siao Tan",
                        "8080224",
                        null,
                        "REGISTER",
                        LocalDateTime.of(
                                2021,
                                03,
                                02,
                                14,
                                0))
                );

        SMSController.getInstance()
                .sendSMS(new SMS(
                        "+639171582496",
                        "Pasta House",
                        "Siao Tan",
                        "8080224",
                        null,
                        "Tan, Siao",
                        LocalDateTime.of(
                                2021,
                                03,
                                02,
                                15,
                                0))
                );
    }

    private static void initScenario() {
        //SCENARIO  2nd Part (PISO PIZZA) -----------------------

        SMSController.getInstance()
                .sendSMS(new SMS(
                        "+639563026795",
                        "Pizza Place",
                        "Juan dela Cruz",
                        "1234555",
                        null,
                        "PROMO",
                        LocalDateTime.of(
                                2021,
                                03,
                                02,
                                11,
                                0))
                );

        SMSController.getInstance()
                .sendSMS(new SMS(
                        "+639563026795",
                        "Pizza Place",
                        "Juan dela Cruz",
                        "1234555",
                        null,
                        "REGISTER",
                        LocalDateTime.of(
                                2021,
                                03,
                                02,
                                11,
                                0))
                );

        SMSController.getInstance()
                .sendSMS(new SMS(
                        "+639563026795",
                        "Pizza Place",
                        "Juan dela Cruz",
                        "1234555",
                        null,
                        "dela Cruz, Juan",
                        LocalDateTime.of(
                                2021,
                                03,
                                02,
                                11,
                                0))
                );

        // ------------------------------------------

        //SCENARIO  (HAPPY HOUR) -----------------------

        SMSController.getInstance()
                .sendSMS(new SMS(
                        "+639563026795",
                        "Tap Station",
                        "Juan dela Cruz",
                        "4551700",
                        null,
                        "HAPPYHOUR",
                        LocalDateTime.of(
                                2022,
                                02,
                                14,
                                20,
                                1))
                );

        SMSController.getInstance()
                .sendSMS(new SMS(
                        "+639563026795",
                        "Tap Station",
                        "Juan dela Cruz",
                        "4551700",
                        null,
                        "REGISTER",
                        LocalDateTime.of(
                                2022,
                                02,
                                14,
                                20,
                                2))
                );

        SMSController.getInstance()
                .sendSMS(new SMS(
                        "+639563026795",
                        "Tap Station",
                        "Juan dela Cruz",
                        "4551700",
                        null,
                        "dela Cruz, Juan",
                        LocalDateTime.of(
                                2022,
                                02,
                                14,
                                20,
                                3))
                );

        // ------------------------------------------

        //SCENARIO  (SPAGHETTI 50) -----------------------

        SMSController.getInstance()
                .sendSMS(new SMS(
                        "+639563026795",
                        "Pasta House",
                        "Juan dela Cruz",
                        "8080224",
                        null,
                        "SPAG50",
                        LocalDateTime.of(
                                2021,
                                06,
                                30,
                                23,
                                55))
                );

        SMSController.getInstance()
                .sendSMS(new SMS(
                        "+639563026795",
                        "Pasta House",
                        "Juan dela Cruz",
                        "8080224",
                        null,
                        "REGISTER",
                        LocalDateTime.of(
                                2021,
                                06,
                                30,
                                23,
                                57))
                );

        SMSController.getInstance()
                .sendSMS(new SMS(
                        "+639563026795",
                        "Pasta House",
                        "Juan dela Cruz",
                        "8080224",
                        null,
                        "dela Cruz, Juan",
                        LocalDateTime.of(
                                2021,
                                06,
                                30,
                                23,
                                59))
                );

        // ------------------------------------------
    }

    private static void createPromos() {
        //CREATE PROMOS 1st Part-----------------------
        Promo promo = new Promo(
                "PROMO",
                "Piso Pizza Promo - Buy a Pizza for only 1 PHP.",
                "1234555",
                LocalDateTime.of(
                        2021,
                        02,
                        01,
                        10,
                        0),
                LocalDateTime.of(
                        2021,
                        06,
                        30,
                        23,
                        59)
        );

        //insert Piso Pizza Promo
        SMSController.getInstance().createPromo(promo);

        Promo promo2 = new Promo(
                "HAPPYHOUR",
                "Beer Promo - Buy 1 Take 1 San Miguel Bucket.",
                "4551700",
                LocalDateTime.of(
                        2022,
                        02,
                        14,
                        20,
                        0),
                LocalDateTime.of(
                        2022,
                        02,
                        14,
                        23,
                        59)
        );

        //insert Happy Hour Promo
        SMSController.getInstance().createPromo(promo2);

        Promo promo3 = new Promo(
                "SPAG50",
                "Spaghetti Promo - Buy a Spaghetti for 50% off.",
                "8080224",
                LocalDateTime.of(
                        2020,
                        03,
                        01,
                        0,
                        0),
                LocalDateTime.of(
                        2021,
                        03,
                        30,
                        23,
                        59)
        );

        //insert Spaghetti 50 Promo
        SMSController.getInstance().createPromo(promo3);

        // -------------------------------
    }
}
