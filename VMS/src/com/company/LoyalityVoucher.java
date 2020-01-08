package com.company;

public class LoyalityVoucher extends Voucher {

    public LoyalityVoucher(float discount,
                           Integer voucherId,
                           String voucherCode,
                           Integer campaignId,
                           String email){
        super(voucherId,voucherCode,campaignId,email,discount,"Loyality");
    }

    public float getDiscount(){return this.value;}
    public void setDiscount(Integer discount){this.value = discount;}

    public String toString(){
        StringBuilder sb = new StringBuilder();
        sb.append("LoyalityVoucher din cadrul campaniei de ID: ");
        sb.append(getCampaignId()+"\n");
        sb.append("VoucherId: " + getVoucherId()+"\n");
        sb.append("VoucherCode: "+ getVoucherCode()+"\n");
        sb.append("Email user: "+ getEmail()+"\n");
        sb.append("Discount: " + getDiscount()+"\n");
        sb.append("Statusul: " + getVoucherStatusType()+"\n");
        return sb.toString();
    }
}
