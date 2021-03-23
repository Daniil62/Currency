package ru.job4j.currency.data_base;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
//import java.util.ArrayList;
import java.util.ArrayList;
import java.util.List;
import ru.job4j.currency.model.Item;

public class CurrencyDbHelper extends SQLiteOpenHelper {
    private static final String DB = "currency_data_base";
    private static final int VERSION = 1;
    private final SQLiteDatabase readableDb = this.getReadableDatabase();
    private final SQLiteDatabase writableDb = this.getWritableDatabase();
    private final String TAB = "_TABLE";
    private final String CURRENCY = "_CURRENCY";
    private final String NAME = "_NAME";
    private final String DATE = "_DATE";
    private final String RATE = "_RATE";
    private final String MOVE = "_MOVE";
    public CurrencyDbHelper(Context context) {
        super (context, DB, null, VERSION);
    }
    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL("create table "
                + DbSchema.EurTable.TAB_NAME + " ("
                + "_id integer primary key autoincrement, "
                + DbSchema.EurTable.Cols.CURRENCY + " text,"
                + DbSchema.EurTable.Cols.NAME + " text,"
                + DbSchema.EurTable.Cols.DATE + " text,"
                + DbSchema.EurTable.Cols.RATE + " real,"
                + DbSchema.EurTable.Cols.MOVE + " real"
                + ")");
        sqLiteDatabase.execSQL("create table "
                + DbSchema.RubTable.TAB_NAME + " ("
                + "_id integer primary key autoincrement, "
                + DbSchema.RubTable.Cols.CURRENCY + " text,"
                + DbSchema.RubTable.Cols.NAME + " text,"
                + DbSchema.RubTable.Cols.DATE + " text,"
                + DbSchema.RubTable.Cols.RATE + " real,"
                + DbSchema.RubTable.Cols.MOVE + " real"
                + ")");
        sqLiteDatabase.execSQL("create table "
                + DbSchema.UsdTable.TAB_NAME + " ("
                + "_id integer primary key autoincrement, "
                + DbSchema.UsdTable.Cols.CURRENCY + " text,"
                + DbSchema.UsdTable.Cols.NAME + " text,"
                + DbSchema.UsdTable.Cols.DATE + " text,"
                + DbSchema.UsdTable.Cols.RATE + " real,"
                + DbSchema.UsdTable.Cols.MOVE + " real"
                + ")");
    }
    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
    }
    public void loadItems(List<Item> items) {
        if (items != null && items.size() > 0) {
            Item item = items.get(0);
            if (checkToEmptiness(item) || (isItNewDate(item) && checkUpdates(items))) {
                writableDb.delete(item.getCurrency() + TAB,
                        null, null);
                ContentValues values = new ContentValues();
                for (Item i : items) {
                    String currency = i.getCurrency();
                    values.put(currency + CURRENCY, currency);
                    values.put(currency + NAME, i.getName());
                    values.put(currency + DATE, i.getDate());
                    values.put(currency + RATE, i.getRate());
                    values.put(currency + MOVE, i.getMove());
                    writableDb.insert(currency + TAB, null, values);
                }
            }
        }
    }
    public List<Item> updateChanges(List<Item> items) {
        if (items != null && items.size() > 0) {
            Item item = items.get(0);
            String currency = item.getCurrency();
            Cursor cursor = readableDb.query(
                    currency + TAB, null, null,
                    null, null, null, null);
            int i = 0;
            cursor.moveToFirst();
            if (isItNewDate(item) && checkUpdates(items)) {
                while (!cursor.isAfterLast()) {
                    item = items.get(i);
                    double oldRate = cursor.getDouble(
                            cursor.getColumnIndex(currency + RATE));
                    double newRate = item.getRate();
                    item.setMove(oldRate - newRate);
                    i++;
                    cursor.moveToNext();
                }
            }
            cursor.close();
        }
        return items;
    }
    private boolean isItNewDate(Item item) {
        String currency = item.getCurrency();
        boolean result = false;
        Cursor cursor = readableDb.query(currency + TAB, null, null,
                null, null, null, null);
        cursor.moveToFirst();
        if (cursor.getCount() == 0 || !cursor.getString(cursor.getColumnIndex(currency + DATE))
                .equals(item.getDate())) {
            result = true;
        }
        cursor.close();
        return result;
    }
    private boolean checkUpdates(List<Item> items) {
        boolean result = false;
        if (items != null && items.size() > 0) {
            Item item = items.get(0);
            String currency = item.getCurrency();
            Cursor cursor = readableDb.query(
                    currency + TAB, null, null, null,
                    null, null, null);
            cursor.moveToFirst();
            int i = 0;
            while (!cursor.isAfterLast()) {
                item = items.get(i);
                double oldRate = cursor.getDouble(
                        cursor.getColumnIndex(currency + RATE));
                double newRate = item.getRate();
                if (oldRate != newRate) {
                    result = true;
                    break;
                }
                i++;
                cursor.moveToNext();
            }
            cursor.close();
        }
        return result;
    }
    private boolean checkToEmptiness(Item item) {
        Cursor cursor = readableDb.query(item.getCurrency() + TAB, null,
                null,  null, null, null, null);
        cursor.moveToFirst();
        boolean result = cursor.getCount() == 0;
        cursor.close();
        return result;
    }
    public List<Item> getItems(Item item) {
        List<Item> result = new ArrayList<>();
        if (item != null) {
            String currency = item.getCurrency();
            Cursor cursor = readableDb.query(currency + TAB, null, null,
                null, null, null, null);
            cursor.moveToFirst();
            while (!cursor.isAfterLast()) {
                result.add(new Item(cursor.getString(cursor.getColumnIndex(currency + CURRENCY)),
                        cursor.getString(cursor.getColumnIndex(currency + NAME)),
                        cursor.getString(cursor.getColumnIndex(currency + DATE)),
                        cursor.getDouble(cursor.getColumnIndex(currency + RATE)),
                        cursor.getDouble(cursor.getColumnIndex(currency + MOVE))));
                cursor.moveToNext();
            }
            cursor.close();
        }
        return result;
    }
}
