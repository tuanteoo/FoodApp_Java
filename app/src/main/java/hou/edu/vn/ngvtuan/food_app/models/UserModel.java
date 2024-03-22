package hou.edu.vn.ngvtuan.food_app.models;

import android.graphics.Bitmap;

public class UserModel  {
    Bitmap avatar;
    String name,phonenumber,email,gender,dateOfBirth;

    public UserModel(Bitmap avatar, String name, String phonenumber, String email, String gender, String dateOfBirth) {
        this.avatar = avatar;
        this.name = name;
        this.phonenumber = phonenumber;
        this.email = email;
        this.gender = gender;
        this.dateOfBirth = dateOfBirth;
    }

    public UserModel() {
    }

    public Bitmap getAvatar() {
        return avatar;
    }

    public void setAvatar(Bitmap avatar) {
        this.avatar = avatar;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhonenumber() {
        return phonenumber;
    }

    public void setPhonenumber(String phonenumber) {
        this.phonenumber = phonenumber;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
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
}
