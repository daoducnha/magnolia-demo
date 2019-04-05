package com.ekino.app.rest.pojos;

public class Lunch {
    String food;
    String beverage;

    public Lunch() {

    }

    public Lunch(String food, String beverage) {
        this.food = food;
        this.beverage = beverage;
    }

    public String getFood() {
        return food;
    }

    public void setFood(String food) {
        this.food = food;
    }

    public String getBeverage() {
        return beverage;
    }

    public void setBeverage(String beverage) {
        this.beverage = beverage;
    }
}
