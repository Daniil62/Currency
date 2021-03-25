package ru.job4j.currency.activity;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import ru.job4j.currency.BuildConfig;
import ru.job4j.currency.R;
import ru.job4j.currency.data_base.CurrencyDbHelper;
import ru.job4j.currency.data_base.DbSchema;
import ru.job4j.currency.interfaces.JsonHolderApi;
import ru.job4j.currency.master.SpinnerMaster;
import ru.job4j.currency.adapter.CurrencyAdapter;
import ru.job4j.currency.model.Currency;
import ru.job4j.currency.model.Item;
import ru.job4j.currency.service.CurrencyPullService;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private static CurrencyDbHelper helper;
    private static MenuItem itemDate;
    private static RecyclerView recycler;
    private static int currencyId = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        helper = new CurrencyDbHelper(this);
        recycler = findViewById(R.id.recycler);
        recycler.setLayoutManager(new LinearLayoutManager(this));
        update();
    }
    public void update() {
        startService(new Intent(this, CurrencyPullService.class));
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.activity_main, menu);
        itemDate = menu.findItem(R.id.menu_date);
        MenuItem itemSpinner = menu.findItem(R.id.menu_spinner);
        Spinner spinner = (Spinner) itemSpinner.getActionView();
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.array, R.layout.support_simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(new SpinnerMaster() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                currencyId = i;
                update();
            }
        });
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.refresh_menu_button) {
            update();
        }
        return super.onOptionsItemSelected(item);
    }
//=================================================================================================
    public static class JsonMaster {
        private String key;
        private final HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        private final OkHttpClient.Builder client = new OkHttpClient.Builder()
                        .addInterceptor(interceptor);
        private final Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://api.exchangeratesapi.io/")
                .addConverterFactory(GsonConverterFactory.create())
                .client(client.build())
                .build();
        private final JsonHolderApi jsonHolderApi = retrofit.create(JsonHolderApi.class);
        private void levelSelector(HttpLoggingInterceptor interceptor) {
            if (BuildConfig.DEBUG) {
                interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
            } else {
                interceptor.setLevel(HttpLoggingInterceptor.Level.BASIC);
            }
        }
        private Call<Currency> callSelector() {
            Call<Currency> call = jsonHolderApi.getLatest();
            key = DbSchema.EurTable.KEY;
            switch (currencyId) {
                case 1: {
                    call = jsonHolderApi.getLatestRUB();
                    key = DbSchema.RubTable.KEY;
                    break;
                }
                case 2: {
                    call = jsonHolderApi.getLatestUSD();
                    key = DbSchema.UsdTable.KEY;
                    break;
                }
            }
            return call;
        }
        public void loader() {
            levelSelector(interceptor);
            callSelector().enqueue(new Callback<Currency>() {
                @RequiresApi(api = Build.VERSION_CODES.N)
                @Override
                public void onResponse(@Nullable Call<Currency> call,
                                       @Nullable Response<Currency> response) {
                    if (response != null && response.body() != null) {
                        update(response.body().convertToItems());
                    }
                }
                @Override
                public void onFailure(@Nullable Call<Currency> call, @Nullable Throwable t) {
                    failureUpdate();
                }
            });
        }
        private void update(List<Item> list) {
            if (list != null && list.size() != 0) {
                helper.loadItems(helper.updateChanges(list));
                recycler.setAdapter(new CurrencyAdapter(helper.getItems(key)));
                itemDate.setTitle(list.get(0).getDate());
            }
        }
        private void failureUpdate() {
            List<Item> items = helper.getItems(key);
            recycler.setAdapter(new CurrencyAdapter(items));
            itemDate.setTitle(items.get(0).getDate());
        }
    }
}