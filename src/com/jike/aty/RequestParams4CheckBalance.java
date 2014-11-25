/*
 * Copyright (c) 2013. Kevin Lee (www.buybal.com).
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.jike.aty;

/**
 * Created by kevin on 13-7-31.
 */
public class RequestParams4CheckBalance extends BaseRequestParams {
    private String taccountId;
    private String userId;
    private String psamNo;
    private String psamPin;
    private String psamTrack;
    private String randomNumber;
    private String currencyCode;
    private String terSerialNo;
    private String formatID;
    private String ksn;
    private String encTracks;
    private String track1Length;
    private String track2Length;
    private String track3Length;
    private String maskedPAN;
    private String expiryDate;
    private String cardHolderName;
    private String cardPwd;
    private String distinguish;
    private String chkmac;//mac校验
    private String track2;//
    private String track3;
    private String field55;//55�?
    private String cardSeqNum;
    
    

    public String getCardSeqNum() {
		return cardSeqNum;
	}

	public void setCardSeqNum(String cardSeqNum) {
		this.cardSeqNum = cardSeqNum;
	}

	public String getChkmac() {
		return chkmac;
	}

	public void setChkmac(String chkmac) {
		this.chkmac = chkmac;
	}

	public String getTrack2() {
		return track2;
	}

	public void setTrack2(String track2) {
		this.track2 = track2;
	}

	public String getTrack3() {
		return track3;
	}

	public void setTrack3(String track3) {
		this.track3 = track3;
	}

	public String getField55() {
		return field55;
	}

	public void setField55(String field55) {
		this.field55 = field55;
	}

	public RequestParams4CheckBalance() {
    }

    public String getTaccountId() {
        return taccountId;
    }

    public void setTaccountId(String taccountId) {
        this.taccountId = taccountId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getPsamNo() {
        return psamNo;
    }

    public void setPsamNo(String psamNo) {
        this.psamNo = psamNo;
    }

    public String getPsamPin() {
        return psamPin;
    }

    public void setPsamPin(String psamPin) {
        this.psamPin = psamPin;
    }

    public String getPsamTrack() {
        return psamTrack;
    }

    public void setPsamTrack(String psamTrack) {
        this.psamTrack = psamTrack;
    }

    public String getRandomNumber() {
        return randomNumber;
    }

    public void setRandomNumber(String randomNumber) {
        this.randomNumber = randomNumber;
    }

    public String getCurrencyCode() {
        return currencyCode;
    }

    public void setCurrencyCode(String currencyCode) {
        this.currencyCode = currencyCode;
    }

    public String getTerSerialNo() {
        return terSerialNo;
    }

    public void setTerSerialNo(String terSerialNo) {
        this.terSerialNo = terSerialNo;
    }

    public String getFormatID() {
        return formatID;
    }

    public void setFormatID(String formatID) {
        this.formatID = formatID;
    }

    public String getKsn() {
        return ksn;
    }

    public void setKsn(String ksn) {
        this.ksn = ksn;
    }

    public String getEncTracks() {
        return encTracks;
    }

    public void setEncTracks(String encTracks) {
        this.encTracks = encTracks;
    }

    public String getTrack1Length() {
        return track1Length;
    }

    public void setTrack1Length(String track1Length) {
        this.track1Length = track1Length;
    }

    public String getTrack2Length() {
        return track2Length;
    }

    public void setTrack2Length(String track2Length) {
        this.track2Length = track2Length;
    }

    public String getTrack3Length() {
        return track3Length;
    }

    public void setTrack3Length(String track3Length) {
        this.track3Length = track3Length;
    }

    public String getMaskedPAN() {
        return maskedPAN;
    }

    public void setMaskedPAN(String maskedPAN) {
        this.maskedPAN = maskedPAN;
    }

    public String getExpiryDate() {
        return expiryDate;
    }

    public void setExpiryDate(String expiryDate) {
        this.expiryDate = expiryDate;
    }

    public String getCardHolderName() {
        return cardHolderName;
    }

    public void setCardHolderName(String cardHolderName) {
        this.cardHolderName = cardHolderName;
    }

    public String getCardPwd() {
        return cardPwd;
    }

    public void setCardPwd(String cardPwd) {
        this.cardPwd = cardPwd;
    }

    public String getDistinguish() {
        return distinguish;
    }

    public void setDistinguish(String distinguish) {
        this.distinguish = distinguish;
    }

    @Override
    public String toString() {
        return "RequestParams4CheckBalance{" +
                "taccountId='" + taccountId + '\'' +
                ", userId='" + userId + '\'' +
                ", psamNo='" + psamNo + '\'' +
                ", psamPin='" + psamPin + '\'' +
                ", psamTrack='" + psamTrack + '\'' +
                ", randomNumber='" + randomNumber + '\'' +
                ", currencyCode='" + currencyCode + '\'' +
                ", terSerialNo='" + terSerialNo + '\'' +
                ", formatID='" + formatID + '\'' +
                ", ksn='" + ksn + '\'' +
                ", encTracks='" + encTracks + '\'' +
                ", track1Length='" + track1Length + '\'' +
                ", track2Length='" + track2Length + '\'' +
                ", track3Length='" + track3Length + '\'' +
                ", maskedPAN='" + maskedPAN + '\'' +
                ", expiryDate='" + expiryDate + '\'' +
                ", cardHolderName='" + cardHolderName + '\'' +
                ", cardPwd='" + cardPwd + '\'' +
                ", distinguish='" + distinguish + '\'' +
                '}';
    }
}
