package com.company;

import java.util.Random;

public class StrategyA implements IStrategy{

    @Override
    public void execute(Campaign c) {
        User[] observers = c.getObservers();
        int count = observers.length;
        Random r = new Random();
        int choice = r.nextInt(count);
        User choosenUser = observers[choice];
        choosenUser.getReceivedVouchers().addVoucher(new GitfVoucher(
                100,
                Campaign.getNewVoucherId(),
                String.valueOf(Campaign.getNewVoucherCode()),
                c.getCampaignId(),
                choosenUser.getUserEmail()
        ));
    }
}

