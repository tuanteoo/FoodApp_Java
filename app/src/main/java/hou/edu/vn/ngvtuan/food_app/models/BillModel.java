package hou.edu.vn.ngvtuan.food_app.models;

import java.util.ArrayList;

public class BillModel {
   private String billKey,userID,dateBill,dateCompletion
           ,quantity,totalPrice,paymentMethod
           ,statusBill,username,address,phonenumber;
    private ArrayList<CartModel> cartModels;
    public BillModel() {
    }

    public BillModel(String billKey, String userID, String dateBill
            , String dateCompletion, String quantity, String totalPrice
            , String paymentMethod, String statusBill, String username,
                     String address, String phonenumber, ArrayList<CartModel> cartModels) {
        this.billKey = billKey;
        this.userID = userID;
        this.dateBill = dateBill;
        this.dateCompletion = dateCompletion;
        this.quantity = quantity;
        this.totalPrice = totalPrice;
        this.paymentMethod = paymentMethod;
        this.statusBill = statusBill;
        this.username = username;
        this.address = address;
        this.phonenumber = phonenumber;
        this.cartModels = cartModels;
    }

    public String getUserID() {
        return userID;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhonenumber() {
        return phonenumber;
    }

    public void setPhonenumber(String phonenumber) {
        this.phonenumber = phonenumber;
    }

    public ArrayList<CartModel> getCartModels() {
        return cartModels;
    }

    public void setCartModels(ArrayList<CartModel> cartModels) {
        this.cartModels = cartModels;
    }

    public String getBillKey() {
        return billKey;
    }

    public void setBillKey(String billKey) {
        this.billKey = billKey;
    }

    public String getDateBill() {
        return dateBill;
    }

    public void setDateBill(String dateBill) {
        this.dateBill = dateBill;
    }

    public String getDateCompletion() {
        return dateCompletion;
    }

    public void setDateCompletion(String dateCompletion) {
        this.dateCompletion = dateCompletion;
    }

    public String getQuantity() {
        return quantity;
    }

    public void setQuantity(String quantity) {
        this.quantity = quantity;
    }

    public String getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(String totalPrice) {
        this.totalPrice = totalPrice;
    }

    public String getPaymentMethod() {
        return paymentMethod;
    }

    public void setPaymentMethod(String paymentMethod) {
        this.paymentMethod = paymentMethod;
    }

    public String getStatusBill() {
        return statusBill;
    }

    public void setStatusBill(String statusBill) {
        this.statusBill = statusBill;
    }

}
