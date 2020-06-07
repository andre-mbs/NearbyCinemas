package com.example.ihc3;

public class Theater {
    private String name;
    private String city;
    private String address;
    private int phone;
    private int moviesNum;

    public Theater(String name, String city, String address, int phone) {
        this.name = name;
        this.city = city;
        this.address = address;
        this.phone = phone;
        this.moviesNum = 0;
    }

    public String getName() {
        return name;
    }

    public String getCity() {
        return city;
    }

    public String getAddress() {
        return address;
    }

    public int getPhone() {
        return phone;
    }

    public int getMoviesNum() {
        return moviesNum;
    }
}

