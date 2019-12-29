package com.company;

public class LoyalityVoucher extends Voucher {

    private float discount;

    public LoyalityVoucher(float discount,
                           Integer voucherId,
                           String voucherCode,
                           Integer campaignId,
                           String email){
        super(voucherId,voucherCode,campaignId,email);
        this.discount = discount;
    }

    public float getDiscount(){return this.discount;}
    public void setDiscount(Integer discount){this.discount = discount;}

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
