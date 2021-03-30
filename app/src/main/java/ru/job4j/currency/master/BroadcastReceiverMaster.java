package ru.job4j.currency.master;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;
import ru.job4j.currency.service.CurrencyPullService;

public class BroadcastReceiverMaster extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        if (intent.getAction().equals(Intent.ACTION_BOOT_COMPLETED)
                || intent.getAction().equals(Intent.ACTION_REBOOT)) {
            CurrencyPullService.enqueueWork(context, intent);
            Toast.makeText(context, "Currency rates update", Toast.LENGTH_LONG).show();
        }
    }
}
