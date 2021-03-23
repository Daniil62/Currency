package ru.job4j.currency.model;

import com.google.gson.annotations.SerializedName;
import java.util.Arrays;
import java.util.List;
import androidx.annotation.NonNull;
import ru.job4j.currency.model.Item;

public class Rates {
    @SerializedName("RUB")
    private double rub;
    @SerializedName("AUD")
    private double aud;
    @SerializedName("BGN")
    private double bgn;
    @SerializedName("BRL")
    private double brl;
    @SerializedName("CAD")
    private double cad;
    @SerializedName("CHF")
    private double chf;
    @SerializedName("CNY")
    private double cny;
    @SerializedName("CZK")
    private double czk;
    @SerializedName("DKK")
    private double dkk;
    @SerializedName("EUR")
    private double eur;
    @SerializedName("GBP")
    private double gbp;
    @SerializedName("HKD")
    private double hkd;
    @SerializedName("HRK")
    private double hrk;
    @SerializedName("HUF")
    private double huf;
    @SerializedName("IDR")
    private double idr;
    @SerializedName("ILS")
    private double ils;
    @SerializedName("INR")
    private double inr;
    @SerializedName("ISK")
    private double isk;
    @SerializedName("JPY")
    private double jpy;
    @SerializedName("KRW")
    private double krw;
    @SerializedName("MXN")
    private double mxn;
    @SerializedName("MYR")
    private double myr;
    @SerializedName("NOK")
    private double nok;
    @SerializedName("NZD")
    private double nzd;
    @SerializedName("PHP")
    private double php;
    @SerializedName("PLN")
    private double pln;
    @SerializedName("RON")
    private double ron;
    @SerializedName("SEK")
    private double sek;
    @SerializedName("SGD")
    private double sgd;
    @SerializedName("THB")
    private double thb;
    @SerializedName("TRY")
    private double _try;
    @SerializedName("USD")
    private double usd;
    @SerializedName("ZAR")// 26
    private double zar;
    public double getAud() {
        return aud;
    }
    public double getBgn() {
        return bgn;
    }
    public double getBrl() {
        return brl;
    }
    public double getCad() {
        return cad;
    }
    public double getChf() {
        return chf;
    }
    public double getCny() {
        return cny;
    }
    public double getCzk() {
        return czk;
    }
    public double getDkk() {
        return dkk;
    }
    public double getEur() {
        return eur;
    }
    public double getGbp() {
        return gbp;
    }
    public double getHkd() {
        return hkd;
    }
    public double getHrk() {
        return hrk;
    }
    public double getHuf() {
        return huf;
    }
    public double getIdr() {
        return idr;
    }
    public double getIls() {
        return ils;
    }
    public double getInr() {
        return inr;
    }
    public double getIsk() {
        return isk;
    }
    public double getJpy() {
        return jpy;
    }
    public double getKrw() {
        return krw;
    }
    public double getMxn() {
        return mxn;
    }
    public double getMyr() {
        return myr;
    }
    public double getNok() {
        return nok;
    }
    public double getNzd() {
        return nzd;
    }
    public double getPhp() {
        return php;
    }
    public double getPln() {
        return pln;
    }
    public double getRon() {
        return ron;
    }
    public double getRub() {
        return rub;
    }
    public double getSek() {
        return sek;
    }
    public double getSgd() {
        return sgd;
    }
    public double getThb() {
        return thb;
    }
    public double getTry() {
        return _try;
    }
    public double getUsd() {
        return usd;
    }
    public double getZar() {
        return zar;
    }
}
