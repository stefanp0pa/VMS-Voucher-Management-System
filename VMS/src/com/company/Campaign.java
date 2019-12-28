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
                    Date startDate,
                    Date endDate,
                    Integer totalVouchersCount,
                    IStrategy strategyType){
        this.campaignId = campaignId;
        this.campaignName = campaignName;
        this.campaignDescription = campaignDescription;
        this.startDate = startDate;
        this.endDate = endDate;
        this.strategyType = strategyType;

        // la inceput, numarul de vouchere disponibile este egal cu numarul total
        this.totalVouchersCount = totalVouchersCount;
        this.availableVouchersCount = totalVouchersCount;

        // replaced default CampaignStatusType value
        decideCampaignStatusType();

        this.vouchers = new Vector<Voucher>();
    }

    private Integer campaignId;
    private String campaignName;
    private String campaignDescription;

    private Date startDate;
    private Date endDate;

    private Integer totalVouchersCount;
    private Integer availableVouchersCount;

    private IStrategy strategyType;

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
        Voucher newVoucher = null;

        if(voucherType.equals("Gift")){
            this.vouchers.add(new GitfVoucher(
                    value,
                    voucherId,
                    String.valueOf(voucherCode),
                    this.campaignId,
                    email));
        }
        if(voucherType.equals("Loyality")){
            this.vouchers.add(new LoyalityVoucher(
                    value,
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

    public void redeemVoucher(String code, Date date){
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
        //target.setUsedDate(date);
        //target.setVoucherStatusType(Voucher.VoucherStatusType.USED);
        markUsedVoucher(target,date);
    }

    private void markUsedVoucher(Voucher v,Date date){
        // a mai fost folosit
        if(v.getUsedDate() != null)
            return;
        // in afara perioadei campaniei
        if(this.startDate.after(date))
            return;
        if(date.after(this.endDate))
            return;
        if(date.compareTo(this.endDate)!=0)
            return;
        v.setUsedDate(date);
        v.setVoucherStatusType(Voucher.VoucherStatusType.USED);
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

    public Date getStartDate(){return this.startDate;}
    public Date getEndDate(){return this.endDate;}

    public CampaignStatusType getCampaignStatusType(){return this.campaignStatusType;}
    public void setCampaignStatusType(CampaignStatusType newStatus){this.campaignStatusType = newStatus;}

    public void decideCampaignStatusType(){
        Date appDate = VMS.getInstance().getApplicationStartDate();
        if(this.startDate.after(appDate)){
            this.setCampaignStatusType(CampaignStatusType.NEW);
            return;
        }
        if(this.endDate.after(appDate)){
            this.setCampaignStatusType(CampaignStatusType.STARTED);
            return;
        }
        this.setCampaignStatusType(CampaignStatusType.EXPIRED);
    }

    public void setStrategyType(IStrategy strategyType){this.strategyType = strategyType;}

    public void executeStrategy(){this.strategyType.execute(this);}
}
