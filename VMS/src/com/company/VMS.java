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

    public void addCampaign(Campaign campaign){
        Campaign target = getCampaignName(campaign.getCampaignName());
        if(target!=null)
            return;
        this.campaigns.add(campaign);
    }

    public void updateCampaign(Integer id, Campaign campaign){
        Campaign myCampaign = getCampaign(id);
        if(myCampaign == null)
            return;
        if(myCampaign.getCampaignStatusType()== Campaign.CampaignStatusType.STARTED){
            Integer myTotal = myCampaign.getTotalVouchersCount();
            Integer myAvailable = myCampaign.getAvailableVouchersCount();
            Integer myGiven = myTotal-myAvailable;
            if(campaign.getTotalVouchersCount() > myGiven){
                myCampaign.setTotalVouchersCount(campaign.getTotalVouchersCount());
                myCampaign.setAvailableVouchersCount(campaign.getTotalVouchersCount() - myGiven);
            }
            myCampaign.setEndDate(campaign.getEndDate());
            return;
        }
        if(myCampaign.getCampaignStatusType() == Campaign.CampaignStatusType.NEW){
            myCampaign.setCampaignName(campaign.getCampaignName());
            myCampaign.setCampaignDescription(campaign.getCampaignDescription());
            myCampaign.setStrategyType(campaign.getStrategyType());
            myCampaign.setStartDate(campaign.getStartDate());
            myCampaign.setEndDate(campaign.getEndDate());
            myCampaign.setTotalVouchersCount(campaign.getTotalVouchersCount());
            myCampaign.setAvailableVouchersCount(campaign.getTotalVouchersCount());
            if(campaign.getCampaignStatusType() == Campaign.CampaignStatusType.CANCELLED){
                myCampaign.setCampaignStatusType(Campaign.CampaignStatusType.CANCELLED);
            }
            return;
        }
    }

    public void cancelCampaign(Integer id){
        Campaign campaign = getCampaign(id);
        if(campaign == null)
            return;
        if(campaign.getCampaignStatusType() == Campaign.CampaignStatusType.EXPIRED)
            return;
        if(campaign.getCampaignStatusType() == Campaign.CampaignStatusType.CANCELLED)
            return;
        campaign.setCampaignStatusType(Campaign.CampaignStatusType.CANCELLED);
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

    public Campaign getCampaignName(String name){
        for(int i =0; i<campaigns.size(); i++){
            if(campaigns.get(i).getCampaignName().compareTo(name)==0){
                return campaigns.get(i);
            }
        }
        return null;
    }
}
