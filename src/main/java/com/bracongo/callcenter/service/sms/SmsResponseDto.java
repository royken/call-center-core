package com.bracongo.callcenter.service.sms;

import java.io.Serializable;

/**
 *
 * @author vr.kenfack
 */
public class SmsResponseDto implements Serializable{
    
    private String msisdn;
    private int smscount;
    private int code;
    private String reason;
    private String ticket;

    public String getMsisdn() {
        return msisdn;
    }

    public void setMsisdn(String msisdn) {
        this.msisdn = msisdn;
    }

    public int getSmscount() {
        return smscount;
    }

    public void setSmscount(int smscount) {
        this.smscount = smscount;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public String getTicket() {
        return ticket;
    }

    public void setTicket(String ticket) {
        this.ticket = ticket;
    }

    @Override
    public String toString() {
        return "SmsResponseDto{" + "msisdn=" + msisdn + ", smscount=" + smscount + ", code=" + code + ", reason=" + reason + ", ticket=" + ticket + '}';
    }
    
    
    
}
