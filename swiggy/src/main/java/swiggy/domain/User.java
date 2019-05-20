package swiggy.domain;


import org.springframework.stereotype.Component;

import java.util.Date;


/*
 * User bean to get user details from table
 */
@Component
public class User {

    private Integer userIdentifier;

    private String userName;

    private String userPass;

    private String userType;

    private String email;

    private Date createdTime;

    private Date updatedTime;

    private Boolean deleteFlag;

    private Boolean isVerified;

    public Integer getUserIdentifier() {
        return userIdentifier;
    }

    public void setUserIdentifier(Integer userIdentifier) {
        this.userIdentifier = userIdentifier;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserPass() {
        return userPass;
    }

    public void setUserPass(String userPass) {
        this.userPass = userPass;
    }

    public String getUserType() {
        return userType;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setUserType(String userType) {
        this.userType = userType;
    }

    public Date getCreatedTime() {
        return createdTime;
    }

    public void setCreatedTime(Date createdTime) {
        this.createdTime = createdTime;
    }

    public Date getUpdatedTime() {
        return updatedTime;
    }

    public void setUpdatedTime(Date updatedTime) {
        this.updatedTime = updatedTime;
    }

    public Boolean getDeleteFlag() {
        return deleteFlag;
    }

    public void setDeleteFlag(Boolean deleteFlag) {
        this.deleteFlag = deleteFlag;
    }

    public Boolean getIsVerified() {
        return isVerified;
    }

    public void setIsVerified(Boolean isVerified) {
        this.isVerified = isVerified;
    }

    @Override
    public String toString() {
        return "User{" +
                "userIdentifier=" + userIdentifier +
                ", userName='" + userName + '\'' +
                ", userPass='" + userPass + '\'' +
                ", userType='" + userType + '\'' +
                ", email='" + email + '\'' +
                ", createdTime=" + createdTime +
                ", updatedTime=" + updatedTime +
                ", deleteFlag=" + deleteFlag +
                ", isVerified=" + isVerified +
                '}';
    }
}
