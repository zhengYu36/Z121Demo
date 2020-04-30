package com.zy.domain;

import com.mysql.jdbc.Blob;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

public class User implements Serializable{

    private Integer userId;
    private String userName;
    private String userPassword;
    private Blob blobD;
    private BigDecimal score;
    private Date birthday;
    private BigDecimal avg;

    public User() {

    }

    public User(Integer userId, String userName, String userPassword, Blob blobD, BigDecimal score, Date birthday, BigDecimal avg) {
        this.userId = userId;
        this.userName = userName;
        this.userPassword = userPassword;
        this.blobD = blobD;
        this.score = score;
        this.birthday = birthday;
        this.avg = avg;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserPassword() {
        return userPassword;
    }

    public void setUserPassword(String userPassword) {
        this.userPassword = userPassword;
    }

    public Blob getBlobD() {
        return blobD;
    }

    public void setBlobD(Blob blobD) {
        this.blobD = blobD;
    }

    public BigDecimal getScore() {
        return score;
    }

    public void setScore(BigDecimal score) {
        this.score = score;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public BigDecimal getAvg() {
        return avg;
    }

    public void setAvg(BigDecimal avg) {
        this.avg = avg;
    }
}