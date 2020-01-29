import java.util.Vector;

public class StrategyC extends Strategy {

    public StrategyC(){
        strategyName = "C";
    }

    @Override
    public void execute(Campaign c) {
        Vector<User> campaignObservers = c.getObservers();

        int minVouchers = Integer.MAX_VALUE;
        User minUser = null;

        for(int i = 0; i < campaignObservers.size(); i++){
            UserVoucherMap currUserMap = campaignObservers.get(i).getReceivedVouchers();
            Vector<Voucher> currCampaignVouchers = currUserMap.get(c.getCampaignId());
            if(currCampaignVouchers.size() < minVouchers){
                minVouchers = currCampaignVouchers.size();
                minUser = campaignObservers.get(i);
            }
        }

        c.generateVoucher(minUser.getUserEmail(),"Gift",100);
    }
}
