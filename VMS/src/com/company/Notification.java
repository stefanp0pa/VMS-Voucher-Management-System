package com.company;

import java.util.Vector;

public class Notification {
    public enum NotificationType{
        EDIT,CANCEL
    }

    public Notification(){
        this(null,0,null);
    }

    public Notification(String sentDate,
                        Integer campaignId,
                        Vector<Integer> vouchersCode){
        this.sentDate = sentDate;
        this.campaignId = campaignId;
        if(vouchersCode == null){
            this.vouchersCode = new Vector<Integer>();
        }else{
            this.vouchersCode = vouchersCode;
        }

    }

    private String sentDate;
    private Integer campaignId;
    private Vector<Integer> vouchersCode;

    public String getSentDate(){return this.sentDate;}
    public Integer getCampaignId(){return this.campaignId;}
    public Vector<Integer> getVouchersCode(){return this.vouchersCode;}
}
