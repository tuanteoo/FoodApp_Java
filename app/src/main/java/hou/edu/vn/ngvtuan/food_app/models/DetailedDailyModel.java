package hou.edu.vn.ngvtuan.food_app.models;

public class DetailedDailyModel {
    int img;
    String name,des,rating,price,timing;

    public DetailedDailyModel(int img, String name, String des, String rating, String price, String timing) {
        this.img = img;
        this.name = name;
        this.des = des;
        this.rating = rating;
        this.price = price;
        this.timing = timing;
    }


    public int getImg() {
        return img;
    }
    public void setImg(int img) {
        this.img = img;
    }

    public String getName1() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDes() {
        return des;
    }

    public void setDes(String des) {
        this.des = des;
    }

    public String getRating() {
        return rating;
    }

    public void setRating(String rating) {
        this.rating = rating;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getTiming() {
        return timing;
    }

    public void setTiming(String timing) {
        this.timing = timing;
    }
}
