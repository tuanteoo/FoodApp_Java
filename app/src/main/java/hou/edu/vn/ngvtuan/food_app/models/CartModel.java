package hou.edu.vn.ngvtuan.food_app.models;

public class CartModel {
    int id;
    byte[] image;
    String name, rating;
    int quantity, price;

    public CartModel(int id,byte[] image, String name, String rating, int quantity, int price) {
        this.id = id;
        this.image = image;
        this.name = name;
        this.rating = rating;
        this.quantity = quantity;
        this.price = price;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }
}
