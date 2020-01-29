import java.util.Random;
import java.util.Vector;

public class StrategyA extends Strategy {

    public StrategyA(){
        strategyName = "A";
    }

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

