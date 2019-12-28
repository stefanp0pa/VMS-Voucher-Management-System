package com.company;

import java.util.Vector;

public class CampaignVoucherMap extends ArrayMap<String, Vector<Voucher>> {

    public boolean addVoucher(Voucher v){
        if(v == null)
            return false;
        String userEmail = v.getEmail();
        if(this.get(userEmail)==null){
            this.put(userEmail,new Vector<Voucher>());
        }
        this.get(userEmail).add(v);
        return true;
    }

    public boolean removeVoucher(Voucher v){
        if(v == null)
            return false;
        String userEmail = v.getEmail();
        String voucherCode = v.getVoucherCode();
        if(this.get(userEmail) != null){
            Vector<Voucher> userVouchers = this.get(userEmail);
            for(int j = 0; j < userVouchers.size(); j++){
                if(userVouchers.get(j).getVoucherCode() == voucherCode){
                    userVouchers.remove(j);
                    return true;
                }
            }
        }
        return false;
    }
}
