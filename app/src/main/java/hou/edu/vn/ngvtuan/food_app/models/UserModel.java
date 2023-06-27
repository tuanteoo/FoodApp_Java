package hou.edu.vn.ngvtuan.food_app.models;

import java.io.Serializable;

public class UserModel implements Serializable {

    private String username;
    private String gender;
    private String dateOfBirth;
    private String phonenumber;
    private String password;

    public UserModel(String username, String gender, String dateOfBirth, String phonenumber, String password) {
        this.username = username;
        this.gender = gender;
        this.dateOfBirth = dateOfBirth;
        this.phonenumber = phonenumber;
        this.password = password;
    }

    public UserModel() {
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(String dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public String getPhonenumber() {
        return phonenumber;
    }

    public void setPhonenumber(String phonenumber) {
        this.phonenumber = phonenumber;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

}
