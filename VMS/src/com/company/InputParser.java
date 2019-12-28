package com.company;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

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

    public boolean parseUsersInput(String usersInputPath) throws FileNotFoundException {
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
