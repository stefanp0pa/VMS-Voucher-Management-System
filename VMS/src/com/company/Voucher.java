package com.company;

import java.time.LocalDateTime;

public abstract class Voucher {

    public enum VoucherStatusType{
        USED,UNUSED
    }

    public Voucher(Integer voucherId,
                   String voucherCode,
                   Integer campaignId,
                   String email){
        this.voucherId = voucherId;
        this.voucherCode = voucherCode;
        this.campaignId = campaignId;
        this.email = email;
        this.voucherStatusType = VoucherStatusType.UNUSED;
    }

    private Integer voucherId;
    private String voucherCode;
    private LocalDateTime usedDate;
    private String email;
    private Integer campaignId;
    private VoucherStatusType voucherStatusType;

    public Integer getVoucherId(){return this.voucherId;}
    public String getVoucherCode(){return this.voucherCode;}
    public LocalDateTime getUsedDate(){return this.usedDate;}
    public String getEmail(){return this.email;}
    public Integer getCampaignId(){return this.campaignId;}
    public VoucherStatusType getVoucherStatusType(){return this.voucherStatusType;}

    public void setVoucherId(Integer voucherId){this.voucherId = voucherId;}
    public void setVoucherCode(String voucherCode){this.voucherCode = voucherCode;}
    public void setUsedDate(LocalDateTime usedDate){this.usedDate = usedDate;}
    public void setEmail(String email){this.email = email;}
    public void setCampaignId(Integer campaignId){this.campaignId = campaignId;}
    public void setVoucherStatusType(VoucherStatusType type){this.voucherStatusType = type;}

}
