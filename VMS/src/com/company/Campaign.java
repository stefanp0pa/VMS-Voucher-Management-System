package com.company;

import java.time.LocalDateTime;
import java.util.*;

public class Campaign {

    public enum CampaignStatusType {
        NEW, STARTED, EXPIRED, CANCELLED
    }

    public Campaign(Integer campaignId,
                    String campaignName,
                    String campaignDescription,
                    String startDate,
                    String endDate,
                    Integer totalVouchersCount,
                    Vector<Voucher> vouchers){
        this.campaignId = campaignId;
        this.campaignName = campaignName;
        this.campaignDescription = campaignDescription;
        this.startDate = startDate;
        this.endDate = endDate;
        this.totalVouchersCount = totalVouchersCount;
        this.campaignStatusType = CampaignStatusType.NEW;

        if(vouchers==null){
            this.vouchers = new Vector<>();
        }else{
            this.vouchers = vouchers;
        }
    }

    private Integer campaignId;
    private String campaignName;
    private String campaignDescription;

    private String startDate;
    private String endDate;

    private Integer totalVouchersCount;
    private Integer availableVouchersCount;

    private CampaignVoucherMap campaignVoucherMap;
    private CampaignStatusType campaignStatusType;

    private Vector<Voucher> vouchers;
    private Set<User> observers; //we dont want any user duplicates

    private static Integer voucherId = 1;
    private static Integer voucherCode = 999;

    public static Integer getNewVoucherId(){return Campaign.voucherId++;}
    public static Integer getNewVoucherCode(){return Campaign.voucherCode++;}


    public Vector<Voucher> getVouchers(){return this.vouchers;}
    public Voucher getVoucher(String voucherCode){
        Voucher target = null;
        for(int i = 0; i < vouchers.size();i++){
            if(vouchers.get(i).getVoucherCode() == voucherCode)
                target = vouchers.get(i);
        }
        return target;
    }

    public void generateVoucher(String email,String voucherType,float value){

        Integer voucherCode = Campaign.voucherCode++;
        Integer voucherId = Campaign.voucherId++;
        if(voucherType.equals("Gift")){
            this.vouchers.add(new GitfVoucher(value,
                    voucherId,
                    String.valueOf(voucherCode),
                    this.campaignId,
                    email));
        }
        if(voucherType.equals("Loyality")){
            this.vouchers.add(new LoyalityVoucher(value,
                    voucherId,
                    String.valueOf(voucherCode),
                    this.campaignId,
                    email));
        }
        this.campaignVoucherMap
                .addVoucher(this.vouchers.get(this.vouchers.size()-1));

        Vector<User> totalUsers = VMS.getInstance().getUsers();
        for(int i = 0; i < totalUsers.size(); i++){
            if(totalUsers.get(i).getUserEmail() == email){
                this.addObserver(totalUsers.get(i));
                break;
            }
        }
    }

    public void redeemVoucher(String code, LocalDateTime date){
        if(code == null || date == null)
            return;
        Voucher target = null;
        for(int i = 0; i < vouchers.size(); i++){
            if(vouchers.get(i).getVoucherCode() == code){
                target = vouchers.get(i);
                break;
            }
        }
        if(target==null)
            return;
        target.setUsedDate(date);
        target.setVoucherStatusType(Voucher.VoucherStatusType.USED);
    }

    public User[] getObservers(){
        return (User[])this.observers.toArray();
    }

    public void addObserver(User user){
        this.observers.add(user);
    }

    public boolean removeObserver(User user){
        return this.observers.remove(user);
    }

    public void notifyAllObservers(Notification notification){
        Iterator<User> it = observers.iterator();
        while(it.hasNext()){
            it.next().update(notification);
        }
    }

    public Integer getCampaignId(){return this.campaignId;}

    public CampaignVoucherMap getCampaignVoucherMap(){return this.campaignVoucherMap;}
}
