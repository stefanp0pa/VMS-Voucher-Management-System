package com.company;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.charset.IllegalCharsetNameException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Calendar;
import java.util.Date;
import java.util.Scanner;
import java.util.regex.Pattern;

public class InputParser {

    private InputParser(){

    }


    public static boolean parseUsersInput(String usersInputPath) throws IOException {
        Path filePath = Paths.get(usersInputPath);
        Scanner scanner = new Scanner(filePath);
        int usersCount = scanner.nextInt();
        scanner.useDelimiter(Pattern.compile("(\\n)|;"));
        for(int i=0; i<usersCount; i++){
            String userId = scanner.next();
            String userName = scanner.next();
            String userPassword = scanner.next();
            String userEmail = scanner.next();
            String type = scanner.next();
            User.UserType userType = null;
            switch (type){
                case "ADMIN":
                    userType = User.UserType.ADMIN;
                    break;
                case "GUEST":
                    userType = User.UserType.GUEST;
                    break;
                default:
                    userType = null;
                    break;
            }
            /*StringBuilder sb = new StringBuilder();
            sb.append(userId);
            sb.append("|");
            sb.append(userName);
            sb.append("|");
            sb.append(userEmail);
            sb.append("|");
            sb.append(userPassword);
            System.out.println(sb.toString());*/

            VMS.getInstance().addUser(new User(
                Integer.valueOf(userId),
                    userName,
                    userPassword,
                    userEmail,
                    userType
            ));
        }
        return true;
    }

    public static boolean parseCampaignInput(String campaignsInputPath) throws IOException {
        Path filePath = Paths.get(campaignsInputPath);
        Scanner scanner = new Scanner(filePath);

        int campaignsCount = scanner.nextInt();

        scanner.useDelimiter("(\\s+)|-");
        int year = Integer.parseInt(scanner.next());
        int month = Integer.parseInt(scanner.next());
        int day  = Integer.parseInt(scanner.next());

        scanner.useDelimiter("(\\n)|:");
        int hour = Integer.parseInt(scanner.next().trim());
        int minutes = Integer.parseInt(scanner.next());

        Calendar cal = Calendar.getInstance();
        cal.set(year,month-1,day,hour,minutes,0);
        VMS.getInstance().setApplicationStartDate(cal.getTime());

        for(int i = 0; i < campaignsCount; i++){

            scanner.useDelimiter("(\\n)|;");
            //int campaignId = Integer.parseInt(scanner.next());
            Integer campaignId = Integer.parseInt(scanner.next());
            //System.out.println(campaignId);

            String campaignName = scanner.next();
            //System.out.println(campaignName);

            String campaignDescription = scanner.next();
            //System.out.println(campaignDescription);

            scanner.useDelimiter(";|(\\s+)|-");
            year = Integer.parseInt(scanner.next());
            month = Integer.parseInt(scanner.next());
            day  = Integer.parseInt(scanner.next());

            scanner.useDelimiter("(\\s+)|:");
            hour = Integer.parseInt(scanner.next());
            scanner.useDelimiter(":|;");
            minutes = Integer.parseInt(scanner.next());

            cal.set(year,month-1,day,hour,minutes,0);
            Date startDate = cal.getTime();

            //System.out.println(startDate);

            scanner.useDelimiter(";|(\\s+)|-");
            year = Integer.parseInt(scanner.next());
            month = Integer.parseInt(scanner.next());
            day  = Integer.parseInt(scanner.next());

            scanner.useDelimiter("(\\s+)|:");
            hour = Integer.parseInt(scanner.next());
            scanner.useDelimiter(":|;");
            minutes = Integer.parseInt(scanner.next());

            cal.set(year,month-1,day,hour,minutes,0);
            Date endDate = cal.getTime();

            //System.out.println(endDate);

            scanner.useDelimiter(";");
            int budget = Integer.parseInt(scanner.next());
            //System.out.println(budget);

            scanner.useDelimiter(";|(\\n)");
            String strategyType = scanner.next();
            //System.out.println(strategyType);

            IStrategy strategy = null;
            switch (strategyType){
                case "A":
                    //System.out.println("A strategy");
                    strategy = new StrategyA();
                    break;
                case "B":
                    //System.out.println("B strategy");
                    strategy = new StrategyB();
                    break;
                case "C":
                    //System.out.println("C strategy");
                    strategy = new StrategyC();
                    break;
                default:
                    break;
            }

            //System.out.println("One more campaign");

            VMS.getInstance().addCampaign(new Campaign(
               campaignId,
               campaignName,
               campaignDescription,
               startDate,
               endDate,
               budget,
               strategy
            ));
        }

        return true;
    }

    public static boolean parseEventsInput(String eventsInputPath) throws IOException {
        Path filePath = Paths.get(eventsInputPath);
        Scanner scanner = new Scanner(filePath);

        scanner.useDelimiter("(\\s+)|-");
        int year = Integer.parseInt(scanner.next());
        int month = Integer.parseInt(scanner.next());
        int day = Integer.parseInt(scanner.next());

        scanner.useDelimiter("(\\s+)|:");
        int hour = Integer.parseInt(scanner.next());
        int minutes = Integer.parseInt(scanner.next());

        Calendar cal = Calendar.getInstance();
        cal.set(year,month-1,day,hour,minutes,0);
        Date startApplicationDate = cal.getTime();

        scanner.useDelimiter("(\\n)");
        int eventsCount = Integer.parseInt(scanner.next());

        for(int i = 0; i < eventsCount; i++){
            scanner.useDelimiter(";|(\\n)");
            int userId = Integer.parseInt(scanner.next().trim());
            String eventAction = scanner.next();

            //System.out.println("Index " + i);

            System.out.print(userId+" ");
            System.out.println(eventAction);

            switch(eventAction){

                case "generateVoucher":
                    parseGenerateVoucherEvent(userId,scanner);
                    break;

                case "redeemVoucher":
                    parseRedeemVoucherEvent(userId,scanner);
                    break;

                case "getVouchers":
                    parseGetVouchersEvent();
                    break;

                case "getVoucher":
                    parseGetVoucherEvent(scanner);
                    break;

                case "getObservers":
                    parseGetObserversEvent(scanner);
                    break;

                case "getNotifications":
                    parseGetNotificationsEvent();
                    break;

                case "addCampaign":
                    parseAddCampaignEvent(scanner);
                    break;

                case "cancelCampaign":
                    parseCancelCampaignEvent(scanner);
                    break;

                case "editCampaign":
                    parseEditCampaignEvent(scanner);
                    break;

                default:
                    break;
            }
        }

        return true;
    }

    public static boolean writeOutput(String outputFilePath){
        return true;
    }

    public static boolean parseGenerateVoucherEvent(Integer userId, Scanner scanner){
        scanner.useDelimiter(";|(\\n)");
        int campaignId = Integer.parseInt(scanner.next());
        String email = scanner.next();
        String voucherType = scanner.next();
        float value = Float.parseFloat(scanner.next().trim());

        User user = VMS.getInstance().getUserById(userId);
        if(user.getUserType() == User.UserType.ADMIN){
            VMS.getInstance().getCampaign(campaignId).generateVoucher(email,voucherType,value);
        }

        //VMS.getInstance().getCampaign(campaignId).generateVoucher(email,voucherType,value);
        return true;
    }

    public static boolean parseRedeemVoucherEvent(Integer userId,Scanner scanner){
        scanner.useDelimiter(";");
        int campaignId = Integer.parseInt(scanner.next());
        int voucherId = Integer.parseInt(scanner.next());

        scanner.useDelimiter(";|-|(\\s+)");
        int year = Integer.parseInt(scanner.next());
        int month = Integer.parseInt(scanner.next());
        int day = Integer.parseInt(scanner.next());

        scanner.useDelimiter("(\\s+)|:");
        int hour = Integer.parseInt(scanner.next());
        scanner.useDelimiter(":|(\\n)");
        int minutes = Integer.parseInt(scanner.next());

        Calendar cal = Calendar.getInstance();
        cal.set(year,month,day,hour,minutes,0);
        Date redeemDate = cal.getTime();

        if(VMS.getInstance().getUserById(userId).getUserType() == User.UserType.ADMIN){
            String code = VMS.getInstance()
                    .getCampaign(campaignId)
                    .getVoucherById(voucherId)
                    .getVoucherCode();
            VMS.getInstance().getCampaign(campaignId)
                    .redeemVoucher(code,redeemDate);
        }
        return true;
    }

    public static boolean parseAddCampaignEvent(Scanner scanner){
        scanner.useDelimiter(";");
        int campaignId = Integer.parseInt(scanner.next());
        String campaignName = scanner.next();
        String campaignDescription = scanner.next();

        scanner.useDelimiter(";|-");
        int year = Integer.parseInt(scanner.next());
        int month = Integer.parseInt(scanner.next());

        scanner.useDelimiter("-|(\\s+)");
        int day = Integer.parseInt(scanner.next());

        scanner.useDelimiter("(\\s+)|:");
        int hour = Integer.parseInt(scanner.next());

        scanner.useDelimiter(":|;");
        int minutes = Integer.parseInt(scanner.next());

        Calendar cal = Calendar.getInstance();
        cal.set(year,month-1,day,hour,minutes,0);
        Date startDate = cal.getTime();

        scanner.useDelimiter(";|-");
        year = Integer.parseInt(scanner.next());
        month = Integer.parseInt(scanner.next());

        scanner.useDelimiter("-|(\\s+)");
        day = Integer.parseInt(scanner.next());

        scanner.useDelimiter("(\\s+)|:");
        hour = Integer.parseInt(scanner.next());

        scanner.useDelimiter(":|;");
        minutes = Integer.parseInt(scanner.next());

        scanner.useDelimiter(";");
        int budget = Integer.parseInt(scanner.next());

        scanner.useDelimiter(";|(\\n)");
        String strategyType = scanner.next();

        //add campaign

        return true;
    }

    public static boolean parseEditCampaignEvent(Scanner scanner){
        scanner.useDelimiter(";");
        int campaignId = Integer.parseInt(scanner.next());
        String campaignName = scanner.next();
        String campaignDescription = scanner.next();

        scanner.useDelimiter(";|-");
        int year = Integer.parseInt(scanner.next());
        int month = Integer.parseInt(scanner.next());

        scanner.useDelimiter("-|(\\s+)");
        int day = Integer.parseInt(scanner.next());

        scanner.useDelimiter("(\\s+)|:");
        int hour = Integer.parseInt(scanner.next());

        scanner.useDelimiter(":|;");
        int minutes = Integer.parseInt(scanner.next());

        Calendar cal = Calendar.getInstance();
        cal.set(year,month-1,day,hour,minutes,0);
        Date startDate = cal.getTime();

        scanner.useDelimiter(";|-");
        year = Integer.parseInt(scanner.next());
        month = Integer.parseInt(scanner.next());

        scanner.useDelimiter("-|(\\s+)");
        day = Integer.parseInt(scanner.next());

        scanner.useDelimiter("(\\s+)|:");
        hour = Integer.parseInt(scanner.next());

        scanner.useDelimiter(":|;");
        minutes = Integer.parseInt(scanner.next());

        cal.set(year,month-1,day,hour,minutes,0);
        Date endDate = cal.getTime();

        scanner.useDelimiter(";|(\\n)");
        int budget = Integer.parseInt(scanner.next());

        System.out.println(budget);

        return true;
    }

    public static boolean parseCancelCampaignEvent(Scanner scanner){
        scanner.useDelimiter(";|(\\n)");
        int campaignId = Integer.parseInt(scanner.next());
        return true;
    }

    public static boolean parseGetVouchersEvent(){
        //
        return true;
    }

    public static boolean parseGetObserversEvent(Scanner scanner){
        scanner.useDelimiter(";|(\\n)");
        int campaignId = Integer.parseInt(scanner.next());

        //VMS.getInstance().getCampaign(campaignId).getObservers()
        return true;
    }

    public static boolean parseGetNotificationsEvent(){
        // action
        return true;
    }

    public static boolean parseGetVoucherEvent(Scanner scanner){
        scanner.useDelimiter(";|(\\n)");
        int campaignId = Integer.parseInt(scanner.next());
        return true;
    }


}
