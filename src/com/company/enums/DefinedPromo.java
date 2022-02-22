package com.company.enums;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

//Enum class with defined promos
//very useful for verifying the validity of an SMS by referencing
public enum DefinedPromo {
    PROMO("PROMO",
            "Piso Pizza Promo - Buy a Pizza for only 1 PHP.",
            "1234555",
            "Pizza Place",
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
    ),

    HAPPYHOUR("HAPPYHOUR",
            "Beer Promo - Buy 1 Take 1 San Miguel Bucket.",
            "4551700",
            "Tap Station",
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
    ),

    SPAG50("SPAG50",
            "Spaghetti Promo - Buy a Spaghetti for 50% off.",
            "8080224",
            "Pasta House",
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

    private String promoCode;
    private String details;
    private String shortCode;
    private String company;
    private LocalDateTime startDate;
    private LocalDateTime endDate;

    DefinedPromo(String promoCode,
                 String details,
                 String shortCode,
                 String company,
                 LocalDateTime startDate,
                 LocalDateTime endDate) {

        this.promoCode = promoCode;
        this.details = details;
        this.shortCode = shortCode;
        this.company = company;
        this.startDate = startDate;
        this.endDate = endDate;
    }

    public String getPromoCode() {
        return promoCode;
    }

    public void setPromoCode(String promoCode) {
        this.promoCode = promoCode;
    }

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }

    public String getShortCode() {
        return shortCode;
    }

    public void setShortCode(String shortCode) {
        this.shortCode = shortCode;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public LocalDateTime getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDateTime startDate) {
        this.startDate = startDate;
    }

    public LocalDateTime getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDateTime endDate) {
        this.endDate = endDate;
    }

    public String getFormattedStartDate(){
        return this.startDate.format(
                DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
    }

    public String getFormattedEndDate(){
        return this.endDate.format(
                DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
    }
}
