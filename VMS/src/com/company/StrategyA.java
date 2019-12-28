package com.company;

import java.util.Random;
import java.util.Vector;

public class StrategyA implements IStrategy{

    @Override
    public void execute(Campaign c) {

        Vector<User> campaignObservers = c.getObservers();
        int randChoice = new Random().nextInt(campaignObservers.size());
        c.generateVoucher(
                campaignObservers.get(randChoice).getUserEmail(),
                "Gift",
                100
        );

    }
}

