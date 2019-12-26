package com.company;

import java.util.AbstractMap;
import java.util.Iterator;
import java.util.Set;
import java.util.Vector;

public class StrategyB implements IStrategy{

    @Override
    public void execute(Campaign c) {
        User[] users = c.getObservers();
        if(users==null || users.length==0)
            return;
        User winner = null;
        int maxUsed = -1;
        for(int i = 0 ; i < users.length; i++){
            Vector<Voucher> userVouchers = c.getCampaignVoucherMap()
                    .get(users[i].getUserEmail());
            int usedVouchersCount = 0;
            for(int j = 0; j < userVouchers.size(); i++){
                if(userVouchers.get(j).getVoucherStatusType()
                        == Voucher.VoucherStatusType.USED){
                    usedVouchersCount++;
                }
            }
            if(usedVouchersCount > maxUsed){
                maxUsed = usedVouchersCount;
                winner = users[i];
            }
        }

        if(winner == null)
            return;
        winner.getReceivedVouchers().addVoucher(new LoyalityVoucher(
                50,
                Campaign.getNewVoucherId(),
                String.valueOf(Campaign.getNewVoucherCode()),
                c.getCampaignId(),
                winner.getUserEmail()
        ));

    }
}
