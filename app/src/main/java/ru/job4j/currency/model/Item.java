package ru.job4j.currency.model;

import androidx.annotation.NonNull;

public class Item {
    private double move;
    private String currency;
    private final String name;
    private final String date;
    private final double rate;
    public Item(String currency, String name, String date, double rate, double move) {
        this.currency = currency;
        this.name = name;
        this.date = date;
        this.rate = rate;
        this.move = move;
    }
    public double getMove() {
        return move;
    }
    public void setMove(double move) {
        this.move = move;
    }
    public String getCurrency() {
        return currency;
    }
    public void setCurrency(String currency) {
        this.currency = currency;
    }
    public String getName() {
        return name;
    }
    public String getDate() {
        return date;
    }
    public double getRate() {
        return rate;
    }
    @NonNull
    @Override
    public String toString() {
        return name + ": " + rate;
    }
}
