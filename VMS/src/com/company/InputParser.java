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
            StringBuilder sb = new StringBuilder();
            sb.append(userId);
            sb.append("|");
            sb.append(userName);
            sb.append("|");
            sb.append(userEmail);
            sb.append("|");
            sb.append(userPassword);
            System.out.println(sb.toString());

            VMS.getInstance().getUsers().add(new User(
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

            scanner.useDelimiter(";");
            //int campaignId = Integer.parseInt(scanner.next());
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
            String strategyType = scanner.next();
            IStrategy strategy = null;
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

            VMS.getInstance().getCampaigns().add(new Campaign(
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

    public boolean parseEventsInput(String eventsInputPath){
        return true;
    }

    public boolean writeOutput(String outputFilePath){
        return true;
    }


}
