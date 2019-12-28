package com.company;

import java.util.List;
import java.util.Vector;

public class User {

    public enum UserType{
        ADMIN,GUEST
    }

    private static Integer generalUserId = 1;

    private Integer userId;
    private String userName;
    private String userEmail;
    private String userPassword;
    private UserType userType;

    private UserVoucherMap receivedVouchers = new UserVoucherMap() ;
    private Vector<Notification> userNotifications;

    public User(Integer userId,
                String userName,
                String userPassword,
                String userEmail,
                UserType userType){
        this.userId = userId;
        this.userName = userName;
        this.userPassword = userPassword;
        this.userEmail = userEmail;
        this.userType = userType;
    }

    public Integer getUserId(){return this.userId;}
    public String getUserName(){return  this.userName;}
    public String getUserEmail(){return  this.userEmail;}
    public String getUserPassword(){return  this.userEmail;}
    public UserType getUserType(){return this.userType;}
    public UserVoucherMap getReceivedVouchers(){return this.receivedVouchers;}

    public static Integer getGeneralUserId(){return User.generalUserId++;}

    public void update(Notification notification){
        userNotifications.add(notification);
    }

}
