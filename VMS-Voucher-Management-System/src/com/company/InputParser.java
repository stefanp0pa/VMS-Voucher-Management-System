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
            Integer campaignId = Integer.parseInt(scanner.next());

            String campaignName = scanner.next();

            String campaignDescription = scanner.next();

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

            scanner.useDelimiter(";");
            int budget = Integer.parseInt(scanner.next());

            scanner.useDelimiter(";|(\\n)");
            String strategyType = scanner.next();

            Strategy strategy = null;
            switch (strategyType){
                case "A":
                    strategy = new StrategyA();
                    break;
                case "B":
                    strategy = new StrategyB();
                    break;
                case "C":
                    strategy = new StrategyC();
                    break;
                default:
                    break;
            }

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

        VMS.getInstance().setApplicationStartDate(startApplicationDate);

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
                    parseAddCampaignEvent(userId,scanner);
                    break;

                case "cancelCampaign":
                    parseCancelCampaignEvent(userId,scanner);
                    break;

                case "editCampaign":
                    parseEditCampaignEvent(userId,scanner);
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

    private static boolean parseGenerateVoucherEvent(Integer userId, Scanner scanner){
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

    private static boolean parseRedeemVoucherEvent(Integer userId,Scanner scanner){
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
        cal.set(year,month-1,day,hour,minutes,0);
        Date redeemDate = cal.getTime();

        if(VMS.getInstance().getUserById(userId).getUserType() == User.UserType.ADMIN){
            String code = VMS.getInstance()
                    .getCampaign(campaignId)
                    .getVoucherById(voucherId)
                    .getVoucherCode();
            VMS.getInstance().getCampaign(campaignId)
                    .redeemVoucher(code,VMS.convertDateToLocalDateTime(redeemDate));
        }
        return true;
    }

    private static boolean parseAddCampaignEvent(Integer userId,Scanner scanner){
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

        scanner.useDelimiter(";");
        int budget = Integer.parseInt(scanner.next());

        scanner.useDelimiter(";|(\\n)");
        String strategyType = scanner.next();

        //add campaign
        if(VMS.getInstance().getUserById(userId)!=null){
            if(VMS.getInstance().getUserById(userId).getUserType() == User.UserType.ADMIN){
                VMS.getInstance().addCampaign(new Campaign(
                        campaignId,
                        campaignName,
                        campaignDescription,
                        startDate,
                        endDate,
                        budget,
                        VMS.decideStrategyType(strategyType)
                        ));
            }
        }


        return true;
    }

    private static boolean parseEditCampaignEvent(Integer userId,Scanner scanner){
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

        //System.out.println(budget);

        User user = VMS.getInstance().getUserById(userId);
        if(user!=null){
            if(user.getUserType() == User.UserType.ADMIN){
                //verificarea pentru update se face in functia updateCampaign()
                VMS.getInstance().updateCampaign(campaignId,new Campaign(
                        campaignId,
                        campaignName,
                        campaignDescription,
                        startDate,
                        endDate,
                        budget,
                        null
                ));
            }
        }

        return true;
    }

    private static boolean parseCancelCampaignEvent(Integer userId,Scanner scanner){
        scanner.useDelimiter(";|(\\n)");
        int campaignId = Integer.parseInt(scanner.next());
        User user = VMS.getInstance().getUserById(userId);
        if(user!=null){
            if(user.getUserType() == User.UserType.ADMIN){
                VMS.getInstance().cancelCampaign(campaignId);
            }
        }
        return true;
    }

    private static boolean parseGetVouchersEvent(){
        return true;
    }

    private static boolean parseGetObserversEvent(Scanner scanner){
        scanner.useDelimiter(";|(\\n)");
        int campaignId = Integer.parseInt(scanner.next());
        return true;
    }

    private static boolean parseGetNotificationsEvent(){
        return true;
    }

    private static boolean parseGetVoucherEvent(Scanner scanner){
        scanner.useDelimiter(";|(\\n)");
        int campaignId = Integer.parseInt(scanner.next());
        return true;
    }

}
