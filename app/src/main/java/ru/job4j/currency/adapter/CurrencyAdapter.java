package ru.job4j.currency.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import java.util.List;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import ru.job4j.currency.model.Item;
import ru.job4j.currency.R;
import ru.job4j.currency.store.FlagStore;

public class CurrencyAdapter extends RecyclerView.Adapter<CurrencyAdapter.CurrencyHolder> {
    private final List<Item> list;
    public CurrencyAdapter(List<Item> list) {
        this.list = list;
    }
    @Override
    public int getItemViewType(int position) {
        return position;
    }
    @Override
    public long getItemId(int position) {
        return position;
    }
    @NonNull
    @Override
    public CurrencyHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.item_module, parent, false);
        return new CurrencyHolder(view);
    }
    @Override
    public void onBindViewHolder(@NonNull CurrencyHolder holder, int position) {
        Item item = list.get(position);
        ImageView flag = holder.itemView.findViewById(R.id.item_flag_imageView);
        TextView itemCurrency = holder.itemView.findViewById(R.id.item_currency_textView);
        TextView itemRate = holder.itemView.findViewById(R.id.item_rate_textView);
        ImageView indicator = holder.itemView.findViewById(R.id.volatile_indicator_imageView);
        setFlag(flag, item.getName());
        itemCurrency.setText(item.getName());
        itemRate.setText(String.valueOf(item.getRate()));
        setIndicator(indicator, item.getMove());
    }
    private void setFlag(ImageView image, String key) {
        FlagStore fs = new FlagStore();
        image.setImageResource(fs.getFlag(key));
    }
    private void setIndicator(ImageView image, double move) {
        if (move < 0) {
            image.setImageResource(R.drawable.indicator_up);
        }
        else if (move > 0) {
            image.setImageResource(R.drawable.indicator_down);
        }
    }
    @Override
    public int getItemCount() {
        return list.size();
    }
    public static class CurrencyHolder extends RecyclerView.ViewHolder {
        public CurrencyHolder(@NonNull View itemView) {
            super(itemView);
        }
    }
}
