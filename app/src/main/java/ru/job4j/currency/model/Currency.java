package ru.job4j.currency.model;

import android.os.Build;
import com.google.gson.annotations.SerializedName;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import androidx.annotation.RequiresApi;

public class Currency {
    private Rates rates;
    @SerializedName("base")
    private String base;
    @SerializedName("date")
    private String date;
    @RequiresApi(api = Build.VERSION_CODES.N)
    public List<Item> convertToItems() {
        return Stream.of(
                new Item(base,"RUB", date, rates.getRub(), 0),
                new Item(base,"AUD", date, rates.getAud(), 0),
                new Item(base,"BGN", date, rates.getBgn(), 0),
                new Item(base,"BRL", date, rates.getBrl(), 0),
                new Item(base,"CAD", date, rates.getCad(), 0),
                new Item(base,"CHF", date, rates.getChf(), 0),
                new Item(base,"CNY", date, rates.getCny(), 0),
                new Item(base,"CZK", date, rates.getCzk(), 0),
                new Item(base,"DKK", date, rates.getDkk(), 0),
                new Item(base,"EUR", date, rates.getEur(), 0),
                new Item(base,"GBP", date, rates.getGbp(), 0),
                new Item(base,"HKD", date, rates.getHkd(), 0),
                new Item(base,"HRK", date, rates.getHrk(), 0),
                new Item(base,"HUF", date, rates.getHuf(), 0),
                new Item(base,"IDR", date, rates.getIdr(), 0),
                new Item(base,"ILS", date, rates.getIls(), 0),
                new Item(base,"INR", date, rates.getInr(), 0),
                new Item(base,"ISK", date, rates.getIsk(), 0),
                new Item(base,"JPY", date, rates.getJpy(), 0),
                new Item(base,"KRW", date, rates.getKrw(), 0),
                new Item(base,"MXN", date, rates.getMxn(), 0),
                new Item(base,"MYR", date, rates.getMyr(), 0),
                new Item(base,"NOK", date, rates.getNok(), 0),
                new Item(base,"NZD", date, rates.getNzd(), 0),
                new Item(base,"PHP", date, rates.getPhp(), 0),
                new Item(base,"PLN", date, rates.getPln(), 0),
                new Item(base,"RON", date, rates.getRon(), 0),
                new Item(base,"SEK", date, rates.getSek(), 0),
                new Item(base,"SGD", date, rates.getSgd(), 0),
                new Item(base,"THB", date, rates.getThb(), 0),
                new Item(base,"TRY", date, rates.getTry(), 0),
                new Item(base,"USD", date, rates.getUsd(), 0),
                new Item(base,"ZAR", date, rates.getZar(), 0))
                .filter(i -> !i.getName().equals(base)).collect(Collectors.toList());
    }
}
