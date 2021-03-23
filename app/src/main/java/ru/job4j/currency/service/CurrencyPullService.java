package ru.job4j.currency.service;

import android.app.IntentService;
import android.content.Intent;
import android.util.Log;
import androidx.annotation.Nullable;
import ru.job4j.currency.activity.MainActivity;

public class CurrencyPullService extends IntentService {
    private static final String TAG = CurrencyPullService.class.getName();
    private final MainActivity.JsonMaster jsonMaster = new MainActivity.JsonMaster();
    public CurrencyPullService() {
        super("CurrencyPullService");
    }
    @Override
    protected void onHandleIntent(@Nullable Intent intent) {
        jsonMaster.loader();
        Log.d(TAG, "execute a task in a service");
    }
}
