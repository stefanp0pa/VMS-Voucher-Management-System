package com.company;

public class GitfVoucher extends Voucher{

    public GitfVoucher(float availableSum,
                       Integer voucherId,
                       String voucherCode,
                       Integer campaignId,
                       String email){
        super(voucherId,voucherCode,campaignId,email,availableSum,"GiftVoucher");
    }

    public float getAvailableSum(){return this.value;}
    public void setAvailableSum(Integer availableSum){this.value = availableSum;}

    public String toString(){
        StringBuilder sb = new StringBuilder();
        sb.append("GiftVoucher din cadrul campaniei de ID: ");
        sb.append(getCampaignId()+"\n");
        sb.append("VoucherId: " + getVoucherId()+"\n");
        sb.append("VoucherCode: "+ getVoucherCode()+"\n");
        sb.append("Email user: "+ getEmail()+"\n");
        sb.append("AvailableSum: " + getAvailableSum()+"\n");
        sb.append("Statusul: " + getVoucherStatusType()+"\n");
        return sb.toString();
    }
}
