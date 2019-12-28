package com.company;

import java.util.List;

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

    private UserVoucherMap receivedVouchers;
    private List<Notification> userNotifications;

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
