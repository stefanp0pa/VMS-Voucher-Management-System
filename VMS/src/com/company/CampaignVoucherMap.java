package com.company;

import java.util.Vector;

public class CampaignVoucherMap extends ArrayMap<String, Vector<Voucher>> {

    public boolean addVoucher(Voucher v){
        String userEmail = v.getEmail();
        if(this.get(userEmail)==null){
            this.put(userEmail,new Vector<Voucher>());
        }
        this.get(userEmail).add(v);
        return true;
    }
}
