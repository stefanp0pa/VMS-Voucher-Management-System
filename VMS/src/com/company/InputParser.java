package com.company;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
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

    public boolean parseCampaignInput(String campaignsInputPath){
        return true;
    }

    public boolean parseEventsInput(String eventsInputPath){
        return true;
    }

    public boolean writeOutput(String outputFilePath){
        return true;
    }


}
