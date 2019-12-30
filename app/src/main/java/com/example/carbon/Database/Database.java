package com.example.carbon.Database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteQuery;
import android.database.sqlite.SQLiteQueryBuilder;

import androidx.annotation.Nullable;

import com.example.carbon.FeedReaderContract;
import com.example.carbon.Model.cart;

import java.util.ArrayList;
import java.util.List;

public class Database extends SQLiteOpenHelper {
    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "Cart.db";
    private static final String SQL_CREATE_ENTRIES =
            "CREATE TABLE " + FeedReaderContract.FeedEntry.TABLE_NAME + " (" +
                    FeedReaderContract.FeedEntry._ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
                    FeedReaderContract.FeedEntry.COLUMN_NAME_TITLE + " TEXT," +
                    FeedReaderContract.FeedEntry.COLUMN_NAME_TITLE1 + " TEXT," +
                    FeedReaderContract.FeedEntry.COLUMN_NAME_TITLE2 + " TEXT,"  +
                    FeedReaderContract.FeedEntry.COLUMN_NAME_TITLE3 + " TEXT)";

    private static final String SQL_DELETE_ENTRIES =
            "DROP TABLE IF EXISTS " + FeedReaderContract.FeedEntry.TABLE_NAME;

    public Database(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);

    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(
                "create table cart " +
                        "(id integer primary key,Catagory text,Name text,Price text,Quantity text)"
        );
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(SQL_DELETE_ENTRIES);
        onCreate(db);
    }

    public List<cart> getCarts() {
        SQLiteDatabase db = getReadableDatabase();
        SQLiteQueryBuilder qb = new SQLiteQueryBuilder();

        String[] select = {"Catagory", "Name", "Price", "Quantity"};
        String table = "cart";
        qb.setTables(table);
        Cursor c = qb.query(db, select, null, null, null, null, null);
        final List<cart> result = new ArrayList<>();
        if (c.moveToFirst()) {
            do {
                result.add(new cart(c.getString(c.getColumnIndex("Catagory")),
                        c.getString(c.getColumnIndex("Name")),
                        c.getString(c.getColumnIndex("Price")),
                        c.getString(c.getColumnIndex("Quantity"))
                ));
            } while (c.moveToNext());

        }
        return result;
    }

    public int numberOfRows(){
        SQLiteDatabase db = this.getReadableDatabase();
        int numRows = (int) DatabaseUtils.queryNumEntries(db, "cart");
        return numRows;
    }

    public void addCart(cart Cart)
    {
        SQLiteDatabase db=getWritableDatabase();
        ContentValues contentValues=new ContentValues();
        contentValues.put("Catagory",Cart.getCatagory());
        contentValues.put("Name",Cart.getName());
        contentValues.put("Price",Cart.getPrice());
        contentValues.put("Quantity",Cart.getQuantity());
        long newRowId = db.insert(FeedReaderContract.FeedEntry.TABLE_NAME, null, contentValues);
//        String query=String.format("INSERT INTO cart(Catagory,Name,Price,Quantity) VALUES('%s','%s','%s','%s','%s');",
//                Cart.getCatagory(),
//                Cart.getName(),
//                Cart.getPrice(),
//                Cart.getQuantity());
//        db.execSQL(query);
    }

    public void cleanCart()
    {
        SQLiteDatabase db=getWritableDatabase();
        String query=String.format("DELETE FROM cart");
        db.execSQL(query);
    }
}
