package com.company;

import java.util.ArrayList;
import java.util.Vector;

public class VMS {

    private static VMS vmsInstance = null;

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
    public void cancelCampaign(Integer id){}

    public Vector<User> getUsers(){return this.users;}
    public void addUser(User user){this.users.add(user);}


}
