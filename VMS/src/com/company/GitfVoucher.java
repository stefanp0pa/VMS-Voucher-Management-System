package com.company;

public class GitfVoucher extends Voucher{

    private float availableSum;

    public GitfVoucher(float availableSum,
                       Integer voucherId,
                       String voucherCode,
                       Integer campaignId,
                       String email){
        super(voucherId,voucherCode,campaignId,email);
        this.availableSum = availableSum;
    }

    public float getAvailableSum(){return this.availableSum;}
    public void setAvailableSum(Integer availableSum){this.availableSum = availableSum;}

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
