package com.asfmtk.trainy;

import java.util.HashMap;
import java.util.Map;

public class Users {

    private String mEmail;
    private String mPassword;
    private String mUserType;

    public Users(String mEmail, String mPassword, String mUserType) {
        this.mEmail = mEmail;
        this.mPassword = mPassword;
        this.mUserType = mUserType;
    }

    public String getmEmail() {
        return mEmail;
    }

    public void setmEmail(String mEmail) {
        this.mEmail = mEmail;
    }

    public String getmPassword() {
        return mPassword;
    }

    public void setmPassword(String mPassword) {
        this.mPassword = mPassword;
    }

    public String getmUserType() {
        return mUserType;
    }

    public void setmUserType(String mUserType) {
        this.mUserType = mUserType;
    }

    public Map<String, Object> toMap() {
        HashMap<String, Object> result = new HashMap<>();
        if(mEmail != null) {
            result.put("mEmail", mEmail);
        }
        if(mPassword != null) {
            result.put("mPassword", mPassword);
        }
        if(mUserType != null) {
            result.put("mUserType", mUserType);
        }

        return result;
    }

}