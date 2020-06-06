package com.thesis.apphealth.data;

public class UserInfomation  {
    public String getUserID() {
        return UserID;
    }

    public void setUserID(String userID) {
        UserID = userID;
    }

    String UserID,FullName,UserName,Email,PhoneNo,Password;

    public String getFullName() {
        return FullName;
    }

    public void setFullName(String fullName) {
        FullName = fullName;
    }

    public String getUserName() {
        return UserName;
    }

    public void setUserName(String userName) {
        UserName = userName;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        Email = email;
    }

    public String getPhoneNo() {
        return PhoneNo;
    }

    public void setPhoneNo(String phoneNo) {
        PhoneNo = phoneNo;
    }

    public String getPassword() {
        return Password;
    }

    public void setPassword(String password) {
        Password = password;
    }

    public UserInfomation(String fullName, String userName, String email, String phoneNo, String password) {
        FullName = fullName;
        UserName = userName;
        Email = email;
        PhoneNo = phoneNo;
        Password = password;
    }

    public UserInfomation(String fullName) {
        FullName = fullName;
    }
    public UserInfomation(){}

}
