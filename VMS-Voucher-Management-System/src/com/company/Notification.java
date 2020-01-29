package com.company;

import java.util.Date;
import java.util.Vector;

public class Notification {
    public enum NotificationType{
        EDIT,CANCEL
    }

    private Date sentDate;
    private Integer campaignId;
    private Vector<String> vouchersCode;

    public Notification(Date sentDate,
                        Integer campaignId,
                        Vector<String> vouchersCode){
        this.sentDate = sentDate;
        this.campaignId = campaignId;
        if(vouchersCode == null){
            this.vouchersCode = new Vector<String>();
        }else{
            this.vouchersCode = vouchersCode;
        }

    }

    public Date getSentDate(){return this.sentDate;}
    public Integer getCampaignId(){return this.campaignId;}
    public Vector<String> getVouchersCode(){return this.vouchersCode;}
}
