package com.proyek.ibf;

import android.content.Context;
import android.content.SharedPreferences;

import java.util.Date;

/**
 * Created by Abhi on 20 Jan 2018 020.
 */

public class SessionHandler {
    private static final String PREF_NAME = "UserSession";
    private static final String KEY_Foto = "foto";
    private static final String KEY_USERNAME = "username";
    private static final String KEY_EXPIRES = "expires";
    private static final String KEY_FULL_NAME = "full_name";
    private static final String KEY_NO_HP = "no_hp";
    private static final String KEY_EMAIL = "email";
    private static final String KEY_TTL = "ttl";
    private static final String KEY_ALAMAT = "alamat";
    private static final String KEY_POIN = "poin";
    private static final String KEY_EMPTY = "";
    private Context mContext;
    private SharedPreferences.Editor mEditor;
    private SharedPreferences mPreferences;

    public SessionHandler(Context mContext) {
        this.mContext = mContext;
        mPreferences = mContext.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE);
        this.mEditor = mPreferences.edit();
    }

    /**
     * Logs in the user by saving user details and setting session
     *
     * @param username
     * @param fullName
     * @param poin
     */
    public void loginUser(String foto, String username, String fullName,String noHp,String alamat,String ttl,String email,String poin) {
        mEditor.putString(KEY_Foto, foto);
        mEditor.putString(KEY_USERNAME, username);
        mEditor.putString(KEY_FULL_NAME, fullName);
        mEditor.putString(KEY_NO_HP, noHp);
        mEditor.putString(KEY_ALAMAT, alamat);
        mEditor.putString(KEY_TTL, ttl);
        mEditor.putString(KEY_EMAIL, email);
        mEditor.putString(KEY_POIN, poin);
        Date date = new Date();

        //Set user session for next 7 days
        long millis = date.getTime() + (7 * 24 * 60 * 60 * 1000);
        mEditor.putLong(KEY_EXPIRES, millis);
        mEditor.commit();
    }

    /**
     * Checks whether user is logged in
     *
     * @return
     */
    public boolean isLoggedIn() {
        Date currentDate = new Date();

        long millis = mPreferences.getLong(KEY_EXPIRES, 0);

        /* If shared preferences does not have a value
         then user is not logged in
         */
        if (millis == 0) {
            return false;
        }
        Date expiryDate = new Date(millis);

        /* Check if session is expired by comparing
        current date and Session expiry date
        */
        return currentDate.before(expiryDate);
    }

    /**
     * Fetches and returns user details
     *
     * @return user details
     */
    public User getUserDetails() {
        //Check if user is logged in first
        if (!isLoggedIn()) {
            return null;
        }
        User user = new User();
        user.setFoto(mPreferences.getString(KEY_Foto, KEY_EMPTY));
        user.setUsername(mPreferences.getString(KEY_USERNAME, KEY_EMPTY));
        user.setFullName(mPreferences.getString(KEY_FULL_NAME, KEY_EMPTY));
        user.setNoHp(mPreferences.getString(KEY_NO_HP, KEY_EMPTY));
        user.setEmail(mPreferences.getString(KEY_EMAIL, KEY_EMPTY));
        user.setAlamat(mPreferences.getString(KEY_ALAMAT, KEY_EMPTY));
        user.setTtl(mPreferences.getString(KEY_TTL, KEY_EMPTY));
        user.setPoin(mPreferences.getString(KEY_POIN, KEY_EMPTY));
        user.setSessionExpiryDate(new Date(mPreferences.getLong(KEY_EXPIRES, 0)));

        return user;
    }

    /**
     * Logs out user by clearing the session
     */
    public void logoutUser(){
        mEditor.clear();
        mEditor.commit();
    }

}
