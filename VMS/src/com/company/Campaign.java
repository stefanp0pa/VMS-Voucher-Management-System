package com.company;

import java.time.LocalDateTime;
import java.util.*;

public class Campaign {

    public enum CampaignStatusType {
        NEW, STARTED, EXPIRED, CANCELLED
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

    //private Vector<Voucher> vouchers;
    //private Set<User> observers; //we dont want any user duplicates

    private Vector<User> observers;

    private static Integer voucherId = 1;
    private static Integer voucherCode = 999;


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

        this.observers = new Vector<User>();
        this.campaignVoucherMap = new CampaignVoucherMap();
    }

    public static Integer getNewVoucherId(){return Campaign.voucherId++;}
    public static Integer getNewVoucherCode(){return Campaign.voucherCode++;}


    /*public Vector<Voucher> getVouchers(){return this.vouchers;}
    public Voucher getVoucher(String voucherCode){
        Voucher target = null;
        for(int i = 0; i < vouchers.size();i++){
            if(vouchers.get(i).getVoucherCode() == voucherCode)
                target = vouchers.get(i);
        }
        return target;
    }
*/
    public void generateVoucher(String email,String voucherType,float value){

        Integer voucherCode = Campaign.voucherCode++;
        Integer voucherId = Campaign.voucherId++;
        Voucher newVoucher = null;

        if(voucherType.equals("Gift")){
            newVoucher = new GitfVoucher(
                    value,
                    voucherId,
                    String.valueOf(voucherCode),
                    this.campaignId,
                    email);
        }
        if(voucherType.equals("Loyality")){
            newVoucher = new LoyalityVoucher(
                    value,
                    voucherId,
                    String.valueOf(voucherCode),
                    this.campaignId,
                    email);
        }

        if(VMS.getInstance().getUserByEmail(email)!=null){
            if(!this.observers.contains(VMS.getInstance().getUserByEmail(email))){
                addObserver(VMS.getInstance().getUserByEmail(email));
            }
        }

        this.campaignVoucherMap
                .addVoucher(newVoucher);
    }

    public boolean redeemVoucher(String code, Date date){
        if(code == null || date == null)
            return false;
        Voucher target = null;

        Set<Map.Entry<String,Vector<Voucher>>> entries = this.campaignVoucherMap.entrySet();
        for(Map.Entry<String,Vector<Voucher>> e: entries){
            Vector<Voucher> e_value = e.getValue();
            for(int j = 0; j <e_value.size(); j++){
                if(e_value.get(j).getVoucherCode() == code){
                    target = e_value.get(j);
                    break;
                }
            }
            if(target!=null)
                break;
        }

        if(target==null)
            return false;

        // voucher marcat si eliminat din dictionar
        if(!markUsedVoucher(target,date))
            return false;

        // se verifica daca userul mai are cel putin un voucher in cadrul campaniei
        if(this.campaignVoucherMap.get(target.getEmail()).size()<=0){
            for(int i = 0; i < this.observers.size(); i++){
                if(this.observers.get(i).getUserEmail() == target.getEmail()){
                    removeObserver(this.observers.get(i));
                }
            }
        }
        return true;
    }

    private boolean markUsedVoucher(Voucher v,Date date){
        // a mai fost folosit
        if(v.getUsedDate() != null)
            return false;
        // in afara perioadei campaniei
        if(this.startDate.after(date))
            return false;
        if(date.after(this.endDate))
            return false;
        if(date.compareTo(this.endDate)!=0)
            return false;
        v.setUsedDate(date);
        v.setVoucherStatusType(Voucher.VoucherStatusType.USED);

        return this.campaignVoucherMap.removeVoucher(v);
    }

    public Vector<User> getObservers(){return this.observers;}

    public void addObserver(User user){
        this.observers.add(user);
    }

    public boolean removeObserver(User user){ return this.observers.remove(user);}

    public void notifyAllObservers(Notification notification){
       for(int i = 0; i < this.observers.size(); i++){
           this.observers.get(i).update(notification);
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
