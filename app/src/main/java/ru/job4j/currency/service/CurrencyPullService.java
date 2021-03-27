package ru.job4j.currency.service;

import android.content.Context;
import android.content.Intent;
import androidx.annotation.NonNull;
import androidx.core.app.JobIntentService;
import ru.job4j.currency.activity.MainActivity;

public class CurrencyPullService extends JobIntentService {
    private final MainActivity.JsonMaster jsonMaster = new MainActivity.JsonMaster();
    private static final int JOB_ID = 1000;
    @Override
    protected void onHandleWork(@NonNull Intent intent) {
        jsonMaster.loader();
    }
    public static void enqueueWork(Context context, Intent intent) {
        enqueueWork(context, CurrencyPullService.class, JOB_ID, intent);
    }
}
