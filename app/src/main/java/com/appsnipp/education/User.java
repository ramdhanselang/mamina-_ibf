package com.appsnipp.education;

import java.util.Date;

public class User {
    String username;
    String fullName;
    String noHp;
    String email;
    String ttl;
    String alamat;
    String poin;
    Date sessionExpiryDate;

    public void setUsername(String username) {
        this.username = username;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public void setPoin(String poin) {
        this.poin = poin;
    }

    public void setNoHp(String noHp) {
        this.noHp = noHp;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setAlamat(String alamat) {
        this.alamat = alamat;
    }


    public void setTtl(String ttl) {
        this.ttl = ttl;
    }

    public void setSessionExpiryDate(Date sessionExpiryDate) {
        this.sessionExpiryDate = sessionExpiryDate;
    }

    public String getUsername() {
        return username;
    }

    public String getFullName() {
        return fullName;
    }

    public String getNoHp() {
        return noHp;
    }

    public String getTtl() {
        return ttl;
    }

    public String getAlamat() {
        return alamat;
    }

    public String getEmail() {
        return email;
    }


    public String getPoin() {
        return poin;
    }


    public Date getSessionExpiryDate() {
        return sessionExpiryDate;
    }
}
