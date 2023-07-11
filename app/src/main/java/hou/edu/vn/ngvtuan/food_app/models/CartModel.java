package hou.edu.vn.ngvtuan.food_app.models;

public class CartModel {
    byte[] image;
    String name,rating;
    Integer price;

    public CartModel(byte[] image, String name, String rating, Integer price) {
        this.image = image;
        this.name = name;
        this.rating = rating;
        this.price = price;
    }

    public byte[] getImage() {
        return image;
    }

    public void setImage(byte[] image) {
        this.image = image;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRating() {
        return rating;
    }

    public void setRating(String rating) {
        this.rating = rating;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }
}
