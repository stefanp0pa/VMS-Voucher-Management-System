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
}
