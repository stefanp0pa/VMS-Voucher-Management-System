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

    private static InputParser instance = null;

    private InputParser(){

    }

    public static InputParser getInstance(){
        if(InputParser.instance == null){
            InputParser.instance = new InputParser();
        }
        return instance;
    }

    public boolean parseUsersInput(String usersInputPath) throws IOException {
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

    public boolean parseCampaignInput(String campaignsInputPath) throws IOException {
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

    public boolean parseEventsInput(String eventsInputPath) throws IOException {
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

        for(int i = 0; i < 5; i++){
            scanner.useDelimiter(";");
            int userId = Integer.parseInt(scanner.next().trim());
            System.out.println(userId);

            String eventAction = scanner.next();

            System.out.println(eventAction);

            switch(eventAction){

                case "generateVoucher":
                    scanner.useDelimiter("(\\n)|;");
                    System.out.println("Generate Voucher");
                    int campaignId = Integer.parseInt(scanner.next());
                    String email = scanner.next();
                    String voucherType = scanner.next();
                    float value = Float.parseFloat(scanner.next().trim());
                    VMS.getInstance().getCampaign(campaignId).generateVoucher(email,voucherType,value);
                    break;

                default:
                    break;
            }
        }

        return true;
    }

    public boolean writeOutput(String outputFilePath){
        return true;
    }


}
