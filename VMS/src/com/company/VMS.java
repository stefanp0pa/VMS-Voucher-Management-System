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

    private Vector<Campaign> campaigns = new Vector<>();
    private Vector<User> users = new Vector<>();

    public Vector<Campaign> getCampaigns(){return this.campaigns;}

    public Campaign getCampaign(Integer id){
        for(int i = 0; i < campaigns.size(); i++){
            if(campaigns.get(i).getCampaignId() == id)
                return campaigns.get(i);
        }
        return null;
    }

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

    public User getUserByEmail(String email){
        for(int i =0 ; i< getUsers().size(); i++){
            if(getUsers().get(i).getUserEmail().equals(email)){
                return getUsers().get(i);
            }
        }

        return null;
    }
    public User getUserById(Integer id){
        for(int i = 0; i < getUsers().size(); i++){
            if(getUsers().get(i).getUserId() == id)
                return getUsers().get(i);
        }
        return null;
    }
    public User getUserByName(String name){
        for(int i = 0; i < users.size(); i++){
            if(users.get(i).getUserName().compareTo(name) == 0){
                return users.get(i);
            }
        }
        return null;
    }
}
