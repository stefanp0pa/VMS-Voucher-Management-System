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
}
