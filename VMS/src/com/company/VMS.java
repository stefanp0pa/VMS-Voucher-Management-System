package com.company;

import java.util.ArrayList;
import java.util.Date;
import java.util.Vector;

public class VMS {

    private static VMS vmsInstance = null;

    private Date applicationStartDate;

    private VMS(){

    }

    public static VMS getInstance(){
        if(VMS.vmsInstance == null){
            VMS.vmsInstance = new VMS();
        }
        return VMS.vmsInstance;
    }

    private Vector<Campaign> campaigns;
    private Vector<User> users;

    public Vector<Campaign> getCampaigns(){return this.campaigns;}
    public Campaign getCampaign(Integer id){return null;}
    public void addCampaign(Campaign campaign){this.campaigns.add(campaign);}
    public void updateCampaign(Integer id, Campaign campaign){}

    public void cancelCampaign(Integer id){
        Campaign target = null;
        for(int i = 0; i < this.campaigns.size(); i++){
            if(this.campaigns.get(i).getCampaignId() == id){
                target = this.campaigns.get(i);
                break;
            }
        }
        if(target==null)
            return;

        if(target.getCampaignStatusType() == Campaign.CampaignStatusType.STARTED){
            target.setCampaignStatusType(Campaign.CampaignStatusType.CANCELLED);
            return;
        }

        if(target.getCampaignStatusType() == Campaign.CampaignStatusType.NEW){
            target.setCampaignStatusType(Campaign.CampaignStatusType.CANCELLED);
            return;
        }
    }

    public Vector<User> getUsers(){return this.users;}
    public void addUser(User user){this.users.add(user);}

    public Date getApplicationStartDate(){return this.applicationStartDate; }
    public void setApplicationStartDate(Date applicationStartDate){
        this.applicationStartDate = applicationStartDate;
    }


}
