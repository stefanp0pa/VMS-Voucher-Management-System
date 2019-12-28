package com.company;

import java.util.Calendar;
import java.util.Date;
import java.util.Map;
import java.util.Vector;

public class Main {

    public static void main(String[] args) {
	// write your code here
       /* Calendar cal = Calendar.getInstance();
        cal.set(Calendar.YEAR,2019);
        cal.set(Calendar.MONTH,8);
        cal.set(Calendar.DAY_OF_MONTH,13);
        cal.set(Calendar.HOUR,17);
        cal.set(Calendar.MINUTE,30);
        cal.set(Calendar.SECOND,0);

        Date date1 = cal.getTime();

        cal.set(2019,8,14,5,29,0);
        Date date2 = cal.getTime();

        System.out.println(date1.toString());
        System.out.println(date2.toString());

        if(date1.after(date2)){
            System.out.println("Data1 este dupa data2");
        }else{
            System.out.println("Data2 este dupa data1");
        }*/
        /*
        UserVoucherMap uvm = new UserVoucherMap();
       Voucher a = new GitfVoucher(100,1,"abcd",100,"ala@hmal.com");
       Voucher b = new GitfVoucher(130,2,"adccd",101,"olala@hmal.com");
       Voucher c = new GitfVoucher(999,3,"thda",102,"giani@hmal.com");
       Voucher d = new GitfVoucher(712,4,"zhasa",103,"meringue@hmal.com");
        Voucher e = new LoyalityVoucher(812,5,"addaaasdcd",101,"olala@hmal.com");

       uvm.addVoucher(a);
       uvm.addVoucher(b);
       uvm.addVoucher(c);
       uvm.addVoucher(d);
       uvm.addVoucher(e);

       System.out.println(uvm.size());

       for(Map.Entry<Integer, Vector<Voucher>> t : uvm.entrySet()){
           System.out.print("Key is: " + t.getKey() +" with keys: ");
           for(int i = 0; i < t.getValue().size(); i++){
               System.out.print(t.getValue().get(i).getVoucherCode() + " , ");
           }
           System.out.println();
       }

       uvm.size();*/

    }
}
