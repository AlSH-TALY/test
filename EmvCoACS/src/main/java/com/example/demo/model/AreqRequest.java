package com.example.demo.model;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class AreqRequest {
    private String threeDSServerTransID;
    private String dsTransID;
    private String messageVersion;
    private String messageType;
    private String cardholderInfo;
    private String dsURL;

    // Getters and setters

    public String getThreeDSServerTransID() {
        return threeDSServerTransID;
    }

    public void setThreeDSServerTransID(String threeDSServerTransID) {
        this.threeDSServerTransID = threeDSServerTransID;
    }

    public String getDsTransID() {
        return dsTransID;
    }

    public void setDsTransID(String dsTransID) {
        this.dsTransID = dsTransID;
    }

    public String getMessageVersion() {
        return messageVersion;
    }

    public void setMessageVersion(String messageVersion) {
        this.messageVersion = messageVersion;
    }

    public String getMessageType() {
        return messageType;
    }

    public void setMessageType(String messageType) {
        this.messageType = messageType;
    }

    public String getCardholderInfo() {
        return cardholderInfo;
    }

    public void setCardholderInfo(String cardholderInfo) {
        this.cardholderInfo = cardholderInfo;
    }

    public String getDsURL() {
        return dsURL;
    }

    public void setDsURL(String dsURL) {
        this.dsURL = dsURL;
    }
}
