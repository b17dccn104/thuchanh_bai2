package com.example.buihoangdat_bai2;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

public class SQLiteOrderOpenHelper  extends SQLiteOpenHelper {
    private  static  final String DATABASE_NAME = "OrderDB.db";
    private static  final int DATABASE_VERSION = 1;

    public SQLiteOrderOpenHelper(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String sql = "create table orderr(" +
                "id integer primary key autoincrement," +
                "name text," +
                "dates text," +
                "amount integer," +
                "price real" +
                ")";
        db.execSQL(sql);
    }

    @Override
    public void onOpen(SQLiteDatabase db) {
        super.onOpen(db);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public long addOrder(Order order) {
        ContentValues values = new ContentValues();
        values.put("name", order.getName());
        values.put("dates", order.getDate());
        values.put("amount", order.getAmount());
        values.put("price", order.getPrice());
        SQLiteDatabase sqLiteDatabase = getWritableDatabase();
        return sqLiteDatabase.insert("orderr", null, values);
    }

    public List<Order> getAll() {
        SQLiteDatabase sqLiteDatabase = getReadableDatabase();
        Cursor cursor = sqLiteDatabase.query("orderr", null, null, null, null, null, null);
        List<Order> orders = new ArrayList<>();
        while(cursor.moveToNext()) {
            int id = cursor.getInt(0);
            String name = cursor.getString(1);
            String date = cursor.getString(2);
            int amount = cursor.getInt(3);
            double price = cursor.getDouble(4);
            Order order = new Order(id, name, date, amount, price);
            orders.add(order);
        }
        return orders;
    }

    public Order getById(int id) {
        String sql = "select * from orderr where id = ?";
        String[] args = { String.valueOf(id) };
        SQLiteDatabase sqLiteDatabase = getReadableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery(sql, args);
        if(cursor.moveToNext()) {
            String name = cursor.getString(1);
            String date = cursor.getString(2);
            int amount = cursor.getInt(3);
            double price = cursor.getDouble(4);
            Order order = new Order(id, name, date, amount, price);
            return order;
        }
        return null;
    }

    public List<Order> getByName(String name) {
        String whereClause = "name like ?";
        String[] args = { "%" + name + "%" };
        SQLiteDatabase sqLiteDatabase = getReadableDatabase();
        Cursor cursor = sqLiteDatabase.query("orderr", null, whereClause, args, null, null, null);
        List<Order> orders = new ArrayList<>();
        while(cursor.moveToNext()) {
            int id = cursor.getInt(0);
            String studentName = cursor.getString(1);
            String date = cursor.getString(2);
            int amount = cursor.getInt(3);
            double price = cursor.getDouble(4);
            Order order = new Order(id, name, date, amount, price);
            orders.add(order);
        }
        return orders;
    }

    public int deleteOrder(int id) {
        String whereClause = "id = ?";
        String[] args = { String.valueOf(id) };
        SQLiteDatabase sqLiteDatabase = getWritableDatabase();
        return sqLiteDatabase.delete("orderr", whereClause, args);
    }

    public int updateOrder(Order student) {
        String whereClause = "id = ?";
        String[] args = { String.valueOf(student.getId()) };
        ContentValues values = new ContentValues();
        values.put("name", student.getName());
        values.put("dates", student.getDate());
        values.put("amount", student.getAmount());
        values.put("price", student.getPrice());
        SQLiteDatabase sqLiteDatabase = getWritableDatabase();
        return sqLiteDatabase.update("orderr", values, whereClause, args);
    }

}
