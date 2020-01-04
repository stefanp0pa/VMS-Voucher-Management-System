package com.company;

import java.io.IOException;
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

        try {
            InputParser.parseUsersInput("D:\\Facultate\\Sem1\\POO\\VMS-Voucher-Management-System\\VMStests\\test06\\input\\users.txt");
        } catch (IOException e) {
            e.printStackTrace();
        }

        /*Vector<User> users = VMS.getInstance().getUsers();
        for(int i =0 ; i < users.size(); i++){
            System.out.println(users.get(i).toString());
        }*/

        try {
            InputParser.parseCampaignInput("D:\\Facultate\\Sem1\\POO\\VMS-Voucher-Management-System\\VMStests\\test06\\input\\campaigns.txt");
        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            InputParser.parseEventsInput("D:\\Facultate\\Sem1\\POO\\VMS-Voucher-Management-System\\VMStests\\test06\\input\\events.txt");
        } catch (IOException e) {
            e.printStackTrace();
        }

        int campaignsCount = VMS.getInstance().getCampaigns().size();
        for(int i = 0; i < campaignsCount; i++){
            Vector<User> campaignObs = VMS.getInstance().getCampaigns().get(i).getObservers();
            System.out.println("Campaign " + i);
            for(int j = 0; j < campaignObs.size(); j++){
                System.out.println(campaignObs.get(j).toString());
            }
            System.out.println();
        }

        /*
        Vector<Campaign> campaigns = VMS.getInstance().getCampaigns();
        for(int i = 0; i < campaigns.size(); i++){
            System.out.println("\nCampaignId: " + campaigns.get(i).getCampaignId());
            System.out.println(campaigns.get(i).getCampaignVoucherMap().toString());
            Vector<User> observers = campaigns.get(i).getObservers();
            for(int j =0; j < observers.size(); j++){
                System.out.println(observers.get(j).getUserEmail());
            }
        }

        Vector<User> users = VMS.getInstance().getUsers();
        for(int i = 0; i < users.size(); i++){
            System.out.println("\nUserId: " + users.get(i).getUserId());
            System.out.println(users.get(i).getReceivedVouchers().toString());
        }

        /*User target = VMS.getInstance().getUserByEmail("USER_5_MAIL");
        if(target==null){
            System.out.println("Scheissee");
        }else{
            System.out.println("AAASASSASA");
            System.out.println(target.toString());

        }*/

        LoadFilesFrame lf = new LoadFilesFrame();
    }
}
