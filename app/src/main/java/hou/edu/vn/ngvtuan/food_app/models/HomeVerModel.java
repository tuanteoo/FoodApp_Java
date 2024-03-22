package hou.edu.vn.ngvtuan.food_app.models;

public class HomeVerModel {
    String imageFood,nameFood,descFood,timeCooking,typeFood;

    int priceFood,quantitySold;

    public HomeVerModel() {
    }

    public HomeVerModel(String imageFood, String nameFood, String descFood, String timeCooking, String typeFood, int priceFood, int quantitySold) {
        this.imageFood = imageFood;
        this.nameFood = nameFood;
        this.descFood = descFood;
        this.timeCooking = timeCooking;
        this.typeFood = typeFood;
        this.priceFood = priceFood;
        this.quantitySold = quantitySold;
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

    public String getDescFood() {
        return descFood;
    }

    public void setDescFood(String descFood) {
        this.descFood = descFood;
    }

    public String getTimeCooking() {
        return timeCooking;
    }

    public void setTimeCooking(String timeCooking) {
        this.timeCooking = timeCooking;
    }

    public String getTypeFood() {
        return typeFood;
    }

    public void setTypeFood(String typeFood) {
        this.typeFood = typeFood;
    }

    public int getPriceFood() {
        return priceFood;
    }

    public void setPriceFood(int priceFood) {
        this.priceFood = priceFood;
    }

    public int getQuantitySold() {
        return quantitySold;
    }

    public void setQuantitySold(int quantitySold) {
        this.quantitySold = quantitySold;
    }
}
