package com.example.cashbackapp;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.Nullable;

public class MyDatabaseHelper extends SQLiteOpenHelper {

    private Context context;
    private static final String DATABASE_NAME = "ExpenseStore.db";
    private static final int DATABASE_VERSION = 1;

    private static final String TABLE_NAME = "my_expense";
    private static final String COLUMN_ID = "_id";
    private static final String COLUMN_CATEGORY = "expense_category";
    private static final String COLUMN_AMOUNT = "expense_amount";
    private static final String COLUMN_DATE = "expense_date";

    public MyDatabaseHelper(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String query = "CREATE TABLE " + TABLE_NAME +
                " (" + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                COLUMN_CATEGORY + " TEXT, " +
                COLUMN_AMOUNT + " REAL, " +
                COLUMN_DATE + " TEXT);";
        db.execSQL(query);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }

    public void addExpense(String category, String amount, String date) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();

        cv.put(COLUMN_CATEGORY, category);
        cv.put(COLUMN_AMOUNT, amount);
        cv.put(COLUMN_DATE, date);
        long result = db.insert(TABLE_NAME, null, cv);
        if (result == -1) {
            Toast.makeText(context, "Failed", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(context, "Added Successfully!", Toast.LENGTH_SHORT).show();
        }
    }

    void updateExpense(String row_id, String category, String amount, String date) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(COLUMN_CATEGORY, category);
        cv.put(COLUMN_AMOUNT, amount);
        cv.put(COLUMN_DATE, date);

        long result = db.update(TABLE_NAME, cv, "_id=?", new String[]{row_id});
        if (result == -1) {
            Toast.makeText(context, "Failed", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(context, "Updated Successfully!", Toast.LENGTH_SHORT).show();
        }

    }

    public Cursor readAllData() {
        String query = "SELECT * FROM " + TABLE_NAME;
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = null;
        if (db != null) {
            cursor = db.rawQuery(query, null);
        }
        return cursor;
    }

    public String readPetrolSpend() {
        String query = "SELECT SUM(expense_amount) as expense FROM my_expense WHERE expense_category = 'Petrol' AND strftime('%m', expense_date) =  strftime('%m', 'now') AND strftime('%Y', expense_date) =  strftime('%Y', 'now');";
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor c = db.rawQuery(query, null);
        String result = "";
        if (c.moveToFirst()) result = "" + c.getString(0);
        c.close();
        db.close();
        return result;
    }

    public String readGroceriesSpend() {
        String query = "SELECT SUM(expense_amount) as expense FROM my_expense WHERE expense_category = 'Groceries' AND strftime('%m', expense_date) =  strftime('%m', 'now') AND strftime('%Y', expense_date) =  strftime('%Y', 'now');";
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor c = db.rawQuery(query, null);
        String result = "";
        if (c.moveToFirst()) result = "" + c.getString(0);
        c.close();
        db.close();
        return result;
    }

    public String readEwalletSpend() {
        String query = "SELECT SUM(expense_amount) as expense FROM my_expense WHERE expense_category = 'E-wallet'AND strftime('%m', expense_date) =  strftime('%m', 'now') AND strftime('%Y', expense_date) =  strftime('%Y', 'now');";
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor c = db.rawQuery(query, null);
        String result = "";
        if (c.moveToFirst()) result = "" + c.getString(0);
        c.close();
        db.close();
        return result;
    }

    public String readOthersSpend() {
        String query = "SELECT SUM(expense_amount) as expense FROM my_expense WHERE expense_category = 'Others'AND strftime('%m', expense_date) =  strftime('%m', 'now') AND strftime('%Y', expense_date) =  strftime('%Y', 'now');";
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor c = db.rawQuery(query, null);
        String result = "";
        if (c.moveToFirst()) result = "" + c.getString(0);
        c.close();
        db.close();
        return result;
    }

    public String readTotalSpend() {
        String query = "SELECT SUM(expense_amount) as expense FROM my_expense WHERE strftime('%m', expense_date) =  strftime('%m', 'now') AND strftime('%Y', expense_date) =  strftime('%Y', 'now');";
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor c = db.rawQuery(query, null);
        String result = "";
        if (c.moveToFirst()) result = "" + c.getString(0);
        c.close();
        db.close();
        return result;
    }

    void deleteOneRow(String row_id) {
        SQLiteDatabase db = this.getWritableDatabase();
        long result = db.delete(TABLE_NAME, "_id=?", new String[]{row_id});
        if (result == -1) {
            Toast.makeText(context, "Failed to Delete.", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(context, "Successfully Deleted.", Toast.LENGTH_SHORT).show();
        }
    }

    void deleteAllData() {
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL("DELETE FROM " + TABLE_NAME);
    }

}