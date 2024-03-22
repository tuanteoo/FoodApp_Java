package hou.edu.vn.ngvtuan.food_app.models;

public class DeliAddressModel {

    private String keyAddress,deliAddress,userName,phoneNumber;
    private boolean isSelected;

    public DeliAddressModel() {
    }

    public DeliAddressModel(String keyAddress, String deliAddress, String userName, String phoneNumber, boolean isSelected) {
        this.keyAddress = keyAddress;
        this.deliAddress = deliAddress;
        this.userName = userName;
        this.phoneNumber = phoneNumber;
        this.isSelected = isSelected;
    }

    public String getKeyAddress() {
        return keyAddress;
    }

    public void setKeyAddress(String keyAddress) {
        this.keyAddress = keyAddress;
    }

    public String getDeliAddress() {
        return deliAddress;
    }

    public void setDeliAddress(String deliAddress) {
        this.deliAddress = deliAddress;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public boolean isSelected() {
        return isSelected;
    }

    public void setSelected(boolean selected) {
        isSelected = selected;
    }
}
