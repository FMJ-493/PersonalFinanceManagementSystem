package com.example.FinanceManagementApp.Models;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;

import java.util.Date;

@Entity
public class Transactions {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int transactionID;

    @JsonProperty("userid")
    private int userID;

    @JsonProperty("type")
    private String transType;

    @JsonProperty("amount")
    private double amount;

    @JsonProperty("category")
    private String category;

    @Temporal(TemporalType.TIMESTAMP)
    private Date transDate = new Date();

    @JsonProperty("notes")
    private String notes;

    public Transactions() {
    }

    public Transactions(int transactionID, int userID, String transType, double amount, String category, Date transDate, String notes) {
        this.transactionID = transactionID;
        this.userID = userID;
        this.transType = transType;
        this.amount = amount;
        this.category = category;
        this.transDate = transDate;
        this.notes = notes;
    }

    public int getTransactionID() {
        return transactionID;
    }

    public void setTransactionID(int transactionID) {
        this.transactionID = transactionID;
    }

    public int getUserID() {
        return userID;
    }

    public void setUserID(int userID) {
        this.userID = userID;
    }

    public String getTransType() {
        return transType;
    }

    public void setTransType(String transType) {
        this.transType = transType;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public Date getTransDate() {
        return transDate;
    }

    public void setTransDate(Date transDate) {
        this.transDate = transDate;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }
}