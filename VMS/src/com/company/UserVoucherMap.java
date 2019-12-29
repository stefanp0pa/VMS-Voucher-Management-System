package com.company;

import java.security.KeyStore;
import java.util.*;

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

    public String toString(){
        StringBuilder sb = new StringBuilder();
        sb.append("Dictionarul actual de tip UserVoucher:\n");

        Set<Entry<Integer,Vector<Voucher>>> entries = this.entrySet();
        Iterator<Entry<Integer,Vector<Voucher>>> it = entries.iterator();

        while(it.hasNext()){
            Entry<Integer,Vector<Voucher>> entry = it.next();
            sb.append("CampaignId: ");
            sb.append(entry.getKey());
            Vector<Voucher> vouchers = entry.getValue();
            sb.append(" | Vouchers: ");
            for(int i = 0; i < vouchers.size(); i++){
                sb.append(vouchers.get(i).getVoucherCode() + " ");
            }
            sb.append("\n");
        }

        return sb.toString();
    }
}
