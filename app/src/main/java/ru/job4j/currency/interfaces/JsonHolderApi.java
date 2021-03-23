package ru.job4j.currency.interfaces;

import retrofit2.Call;
import retrofit2.http.GET;
import ru.job4j.currency.model.Currency;

public interface JsonHolderApi {
    @GET("latest")
    Call<Currency> getLatest();
    @GET("latest?base=RUB")
    Call<Currency> getLatestRUB();
    @GET("latest?base=USD")
    Call<Currency> getLatestUSD();
}
