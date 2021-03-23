package ru.job4j.currency.store;

import java.util.HashMap;
import java.util.Map;
import ru.job4j.currency.R;

public class FlagStore {
    private final Map<String, Integer> flags = new HashMap<>(); {
        flags.put("RUB", R.drawable.rub);
        flags.put("AUD", R.drawable.aud);
        flags.put("BGN", R.drawable.bgn);
        flags.put("BRL", R.drawable.brl);
        flags.put("CAD", R.drawable.cad);
        flags.put("CHF", R.drawable.chf);
        flags.put("CNY", R.drawable.cny);
        flags.put("CZK", R.drawable.czk);
        flags.put("DKK", R.drawable.dkk);
        flags.put("EUR", R.drawable.eur);
        flags.put("GBP", R.drawable.gbp);
        flags.put("HKD", R.drawable.hkd);
        flags.put("HRK", R.drawable.hrk);
        flags.put("HUF", R.drawable.huf);
        flags.put("IDR", R.drawable.idr);
        flags.put("ILS", R.drawable.ils);
        flags.put("INR", R.drawable.inr);
        flags.put("ISK", R.drawable.isk);
        flags.put("JPY", R.drawable.jpy);
        flags.put("KRW", R.drawable.krw);
        flags.put("MXN", R.drawable.mxn);
        flags.put("MYR", R.drawable.myr);
        flags.put("NOK", R.drawable.nok);
        flags.put("NZD", R.drawable.nzd);
        flags.put("PHP", R.drawable.php);
        flags.put("PLN", R.drawable.pln);
        flags.put("RON", R.drawable.ron);
        flags.put("SEK", R.drawable.sek);
        flags.put("SGD", R.drawable.sgd);
        flags.put("THB", R.drawable.thb);
        flags.put("TRY", R.drawable._try);
        flags.put("USD", R.drawable.usd);
        flags.put("ZAR", R.drawable.zar);
    }
    public int getFlag(String key) {
        return flags.get(key);
    }
}
