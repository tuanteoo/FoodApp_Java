package hou.edu.vn.ngvtuan.food_app.models;

public class BillModel {
    int id;
    String username,phonenumber,address,date;
    int totalPrice;

    public BillModel(int id, String username, String phonenumber, String address,String date, int totalPrice) {
        this.id = id;
        this.date = date;
        this.username = username;
        this.phonenumber = phonenumber;
        this.address = address;
        this.totalPrice = totalPrice;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPhonenumber() {
        return phonenumber;
    }

    public void setPhonenumber(String phonenumber) {
        this.phonenumber = phonenumber;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public int getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(int totalPrice) {
        this.totalPrice = totalPrice;
    }
}
