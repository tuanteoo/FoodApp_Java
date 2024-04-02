package hou.edu.vn.ngvtuan.food_app.models;

import java.io.Serializable;

public class CartModel implements Serializable {
    String imageFood, nameFood;
    int priceFood, price_quantity, quantity;

    public CartModel() {
    }

    public CartModel(String imageFood, String nameFood, int priceFood, int price_quantity, int quantity) {
        this.imageFood = imageFood;
        this.nameFood = nameFood;
        this.priceFood = priceFood;
        this.price_quantity = price_quantity;
        this.quantity = quantity;
    }

    public String getImageFood() {
        return imageFood;
    }

    public void setImageFood(String imageFood) {
        this.imageFood = imageFood;
    }

    public String getNameFood() {
        return nameFood;
    }

    public void setNameFood(String nameFood) {
        this.nameFood = nameFood;
    }

    public int getPriceFood() {
        return priceFood;
    }

    public void setPriceFood(int priceFood) {
        this.priceFood = priceFood;
    }

    public int getPrice_quantity() {
        return price_quantity;
    }

    public void setPrice_quantity(int price_quantity) {
        this.price_quantity = price_quantity;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}
