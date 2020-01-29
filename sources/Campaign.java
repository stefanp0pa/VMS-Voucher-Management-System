import javax.swing.*;
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

    private Strategy strategyType;

    private CampaignVoucherMap campaignVoucherMap;
    private CampaignStatusType campaignStatusType;

    private Vector<Voucher> vouchers = new Vector<>();

    private Vector<User> observers;

    private Integer voucherId = 1; //unique voucher id per campaign
    private static Integer voucherCode = 1000; //unique voucher code per application


    public Campaign(Integer campaignId,
                    String campaignName,
                    String campaignDescription,
                    Date startDate,
                    Date endDate,
                    Integer totalVouchersCount,
                    Strategy strategyType){
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

    public Integer getNewVoucherId(){return this.voucherId++;}
    public static Integer getNewVoucherCode(){return Campaign.voucherCode++;}

    public boolean generateVoucher(String email,String voucherType,float value){
        if(getCampaignStatusType() == CampaignStatusType.EXPIRED){
            System.out.println("Cannot add for expired campaign");
            return false;
        }

        if(getCampaignStatusType() == CampaignStatusType.CANCELLED){
            System.out.println("Cannot add for cancelled campaign");
            return false;
        }

        if(availableVouchersCount <= 0){
            System.out.println("No more available vouchers");
            return false;
        }


        Integer voucherCode = Campaign.voucherCode++;
        Integer voucherId = getNewVoucherId();
        Voucher newVoucher = null;

        if(voucherType.equals("GiftVoucher")){
            newVoucher = new GitfVoucher(
                    value,
                    voucherId,
                    String.valueOf(voucherCode),
                    this.campaignId,
                    email);
        }
        if(voucherType.equals("LoyaltyVoucher")){
            newVoucher = new LoyalityVoucher(
                    value,
                    voucherId,
                    String.valueOf(voucherCode),
                    this.campaignId,
                    email);
        }

        if(newVoucher == null)
            return false;

        User user = VMS.getInstance().getUserByEmail(email);

        if(user!=null){
            if(!this.observers.contains(user)){
                addObserver(user);
            }
            user.getReceivedVouchers().addVoucher(newVoucher);
        }else{
            System.out.println("Bulsshit!!");
            return false;
        }

        this.vouchers.add(newVoucher);

        boolean result = this.campaignVoucherMap
                .addVoucher(newVoucher);
        if(!result)
            return false;

        this.availableVouchersCount--;

        System.out.println("S-a generat voucher!");
        return true;
    }

    public boolean redeemVoucher(String code, LocalDateTime date){

        if(getCampaignStatusType() == CampaignStatusType.EXPIRED)
            return false;
        if(getCampaignStatusType() == CampaignStatusType.CANCELLED)
            return false;
        if(getCampaignStatusType() == CampaignStatusType.NEW)
            return false;

        Voucher v = getVoucher(code);
        // voucher marcat si eliminat din dictionar
        if(!markUsedVoucher(v,VMS.convertLocalDateTimeToDate(date)))
            return false;

        // se verifica daca userul mai are cel putin un voucher in cadrul campaniei
        if(this.campaignVoucherMap.get(v.getEmail()).size()<=0){
            removeObserver(getObserverByEmail(v.getEmail()));
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

        if(date.compareTo(this.endDate)==0)
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
    public User getObserverByEmail(String email){
        for(int i = 0; i < this.observers.size(); i++){
            if(this.observers.get(i).getUserEmail().equals(email)){
                return this.observers.get(i);
            }
        }
        return null;
    }

    public Integer getCampaignId(){return this.campaignId;}

    public CampaignVoucherMap getCampaignVoucherMap(){return this.campaignVoucherMap;}

    public Date getStartDate(){return this.startDate;}
    public void setStartDate(Date startDate){
        this.startDate = startDate;
        decideCampaignStatusType();
    }

    public Date getEndDate(){return this.endDate;}
    public void setEndDate(Date endDate){
        this.endDate = endDate;
        decideCampaignStatusType();
    }

    public String getCampaignName(){return this.campaignName;}
    public void setCampaignName(String campaignName){this.campaignName = campaignName;}

    public String getCampaignDescription(){return this.campaignDescription;}
    public void setCampaignDescription(String campaignDescription){this.campaignDescription = campaignDescription;}

    public Integer getTotalVouchersCount(){return this.totalVouchersCount;}
    public Integer getAvailableVouchersCount(){return this.availableVouchersCount;}

    public void setTotalVouchersCount(Integer totalVouchersCount){this.totalVouchersCount = totalVouchersCount;}
    public void setAvailableVouchersCount(Integer availableVouchersCount){this.availableVouchersCount = availableVouchersCount;}

    public Vector<Voucher> getVouchers(){return this.vouchers;}
    public Voucher getVoucherById(Integer voucherId){
        for(int i = 0; i < this.vouchers.size(); i++){
            if(vouchers.get(i).getVoucherId() == voucherId){
                return vouchers.get(i);
            }
        }
        return null;
    }
    public Voucher getVoucher(String code){
        for(int i = 0; i < vouchers.size(); i++){
            if(vouchers.get(i).getVoucherCode().equals(code)){
                return vouchers.get(i);
            }
        }
        return null;
    }

    public CampaignStatusType getCampaignStatusType(){return this.campaignStatusType;}
    public void setCampaignStatusType(CampaignStatusType newStatus){this.campaignStatusType = newStatus;}
    public void decideCampaignStatusType(){

        if(getCampaignStatusType() == CampaignStatusType.CANCELLED){
            return;
        }

        Date appDate = VMS.getInstance().getApplicationStartDate();
        if(this.startDate.after(appDate)){
            this.setCampaignStatusType(CampaignStatusType.NEW);
            return;
        }
        if(this.endDate.after(appDate) || appDate.after(this.startDate)){
            this.setCampaignStatusType(CampaignStatusType.STARTED);
            return;
        }
        if(this.startDate.equals(appDate)){
            this.setCampaignStatusType(CampaignStatusType.STARTED);
            return;
        }
        if(this.endDate.equals(appDate)){
            this.setCampaignStatusType(CampaignStatusType.EXPIRED);
            return;
        }

        this.setCampaignStatusType(CampaignStatusType.EXPIRED);
    }

    public Strategy getStrategyType(){return this.strategyType;}
    public static Strategy decideStrategyType(String type){
        if(type.equals("A"))
            return new StrategyA();
        if(type.equals("B"))
            return new StrategyB();
        if(type.equals("C"))
            return new StrategyC();
        return null;
    }

    public void executeStrategy(){this.strategyType.execute(this);}

    public String toString(){
        StringBuilder sb = new StringBuilder();
        sb.append("This is the campaign ");
        sb.append(this.campaignId);
        sb.append(" :\n");
        sb.append("Name: " + this.campaignName + "\n");
        sb.append("Description: " + this.campaignDescription + "\n");
        sb.append("StartDate: " + this.startDate + "\n");
        sb.append("EndDate: " + this.endDate + "\n");
        sb.append("Strategy: " + this.strategyType.toString() + "\n");
        return sb.toString();
    }
}
