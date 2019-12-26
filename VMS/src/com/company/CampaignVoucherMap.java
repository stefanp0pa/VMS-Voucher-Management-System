package com.company;

import java.util.Vector;

public class CampaignVoucherMap extends ArrayMap<String, Vector<Voucher>> {

    public boolean addVoucher(Voucher v){
        String userEmail = v.getEmail();
        if(this.get(userEmail)==null){
            Vector<Voucher> newVector = new Vector<>();
            newVector.add(v);
            this.put(userEmail,new Vector<>(newVector));
        }else{
            this.get(userEmail).add(v);
        }
        return true;
    }
}
