package com.lst.marrakechassistance.Model;

import java.io.Serializable;

public class Hotel implements Serializable {
    private String title;
    private String address;
    private String description;
    private int bed ;
    private int path;
    private int price ;
    private String pic;
    private Boolean wifi;
    private double latitude;
    private double longitude;
    private float distance;

    public void setDistance(float distance) {
        this.distance = distance;
    }

    public float getDistance() {
        return distance;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public double getLatitude() {
        return latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public Hotel(String title, double latitude, double longitude) {
        this.title = title;
        this.latitude = latitude;
        this.longitude = longitude;
    }

    public Hotel(String title, String address, String description, int bed, int path, int price, String pic, Boolean wifi) {
        this.title = title;
        this.address = address;
        this.description = description;
        this.bed = bed;
        this.path = path;
        this.price = price;
        this.pic = pic;
        this.wifi = wifi;
    }

    public Hotel(String title, String address, int price, String pic, double latitude, double longitude) {
        this.title = title;
        this.address = address;
        this.price = price;
        this.pic = pic;
        this.latitude = latitude;
        this.longitude = longitude;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getBed() {
        return bed;
    }

    public void setBed(int bed) {
        this.bed = bed;
    }

    public int getPath() {
        return path;
    }

    public void setPath(int path) {
        this.path = path;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public String getPic() {
        return pic;
    }

    public void setPic(String pic) {
        this.pic = pic;
    }

    public Boolean getWifi() {
        return wifi;
    }

    public void setWifi(Boolean wifi) {
        this.wifi = wifi;
    }
}
