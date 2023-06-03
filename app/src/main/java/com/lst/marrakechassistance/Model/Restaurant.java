package com.lst.marrakechassistance.Model;

public class Restaurant {
    private String titleRestaurant;
    private String pic;


    public Restaurant(String titleRestaurant,String pic
    ) {
        this.titleRestaurant = titleRestaurant;
        this.pic = pic;
    }

    public String getTitleRestaurant() {
        return titleRestaurant;
    }

    public void setTitle(String title) {
        this.titleRestaurant = title;
    }

    public String getPic() {
        return pic;
    }

    public void setPic(String title) {
        this.pic = title;
    }


}
