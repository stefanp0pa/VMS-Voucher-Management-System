package com.company;

import java.util.AbstractMap;
import java.util.Iterator;
import java.util.Set;
import java.util.Vector;

public class StrategyB implements IStrategy{

    public static String strategyName = "B";

    @Override
    public void execute(Campaign c) {

        Vector<User> campaignObservers = c.getObservers();
        int maxUsed = -1; // maximul de vouchere folosite
        User maxUser = null;

        for(int i = 0; i < campaignObservers.size(); i++){
            User currUser = campaignObservers.get(i);
            UserVoucherMap currUserMap = currUser.getReceivedVouchers();
            Vector<Voucher> currCampaignVouchers = currUserMap.get(c.getCampaignId());
            int counting = 0;
            for(int j = 0; j < currCampaignVouchers.size(); j++){
                if(currCampaignVouchers.get(j).getVoucherStatusType() == Voucher.VoucherStatusType.USED)
                    counting++;
            }
            if(counting > maxUsed){
                maxUsed = counting;
                maxUser = currUser;
            }
        }
        c.generateVoucher(maxUser.getUserEmail(),"Loyality",50);
    }
}
