package com.company;

import java.util.Iterator;
import java.util.Set;
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

    public String toString(){
        StringBuilder sb = new StringBuilder();
        sb.append("Dictionarul actual de tip CampaignVoucher:\n");

        Set<Entry<String,Vector<Voucher>>> entries = this.entrySet();
        Iterator<Entry<String,Vector<Voucher>>> it = entries.iterator();

        while(it.hasNext()){
            Entry<String,Vector<Voucher>> entry = it.next();
            sb.append("UserEmail: ");
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
