package com.example.demo.model;

import java.util.UUID;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class AresResponse {
    private String threeDSServerTransID;
    private String dsTransID;
    private String messageVersion;
    private String messageType;
    private String acsTransID=UUID.randomUUID().toString();
    private String transStatus;
    private String eci="06";
    private String dsReferenceNumber="3DS_LOA_DIS_MAST_020200_00563_____shamdy_Abdo";
    public String getDsReferenceNumber() {
		return dsReferenceNumber;
	}

	public void setDsReferenceNumber(String dsReferenceNumber) {
		this.dsReferenceNumber = dsReferenceNumber;
	}

	public String getAcsReferenceNumber() {
		return acsReferenceNumber;
	}

	public void setAcsReferenceNumber(String acsReferenceNumber) {
		this.acsReferenceNumber = acsReferenceNumber;
	}

	public String getAcsOperatorID() {
		return acsOperatorID;
	}

	public void setAcsOperatorID(String acsOperatorID) {
		this.acsOperatorID = acsOperatorID;
	}

	public String getSdkTransID() {
		return sdkTransID;
	}

	public void setSdkTransID(String sdkTransID) {
		this.sdkTransID = sdkTransID;
	}

	public String getAuthenticationValue() {
		return authenticationValue;
	}

	public void setAuthenticationValue(String authenticationValue) {
		this.authenticationValue = authenticationValue;
	}

	private String acsReferenceNumber="3DS_LOA_ACS_BPBT_020200_00389";
    private String acsOperatorID = "acsOperatorUL";
    private String sdkTransID="a766473d-eae8-4bb8-acea-dea4c7f7ae8f";
    private String authenticationValue="xgQRIwAAAAAAAAAAAAAAAAAAAAAA";
 
    // Getters and setters

    public String getEci() {
		return eci;
	}

	public void setEci(String eci) {
		this.eci = eci;
	}

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

    public String getAcsTransID() {
        return acsTransID;
    }

    public void setAcsTransID(String acsTransID) {
        this.acsTransID = acsTransID;
    }

    public String getTransStatus() {
        return transStatus;
    }

    public void setTransStatus(String transStatus) {
        this.transStatus = transStatus;
    }
}
