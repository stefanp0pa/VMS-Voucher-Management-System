package com.company;

import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

public class UserVoucherMap extends ArrayMap<Integer, Vector<Voucher>> {

    public boolean addVoucher(Voucher v){
        Integer campaignId = v.getCampaignId();
        if(this.get(campaignId)==null){
            //Vector<Voucher> newVector = new Vector<>();
            //newVector.add(v);
            this.put(campaignId,new Vector<Voucher>());
        }
        this.get(campaignId).add(v);
        return true;
    }
}
