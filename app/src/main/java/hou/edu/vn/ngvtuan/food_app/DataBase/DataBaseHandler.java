package hou.edu.vn.ngvtuan.food_app.DataBase;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import hou.edu.vn.ngvtuan.food_app.models.BillModel;
import hou.edu.vn.ngvtuan.food_app.models.CartModel;
import hou.edu.vn.ngvtuan.food_app.models.UserModel;

public class DataBaseHandler extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "foodapp.db";
    private static final Integer DATABASE_VERSION = 1;
    private static final SQLiteDatabase.CursorFactory DATABASE_FACTORY = null;

    public DataBaseHandler(@Nullable Context context) {
        super(context, DATABASE_NAME, DATABASE_FACTORY, DATABASE_VERSION);
    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE IF NOT EXISTS user(username TEXT,phonenumber TEXT PRIMARY KEY,password TEXT,gender TEXT,dateofbirth TEXT )");
        db.execSQL("CREATE TABLE IF NOT EXISTS orderlist(id INTEGER PRIMARY KEY AUTOINCREMENT, image BLOB,foodname TEXT,rating TEXT,quantity INTEGER,price INTEGER)");
        db.execSQL("CREATE TABLE IF NOT EXISTS listBill(id INTEGER PRIMARY KEY AUTOINCREMENT,username TEXT,phonenumber TEXT,address TEXT,price INTEGER,date TEXT )");
    }
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS user");
        db.execSQL("DROP TABLE IF EXISTS orderlist");
        db.execSQL("DROP TABLE IF EXISTS listBill");
        onCreate(db);
    }

    //Insert Data To Table User
    public Boolean InsertData (String username,String gender,String dateofbirth,String phonenumber,String password){
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();

        contentValues.put("username",username);
        contentValues.put("phonenumber",phonenumber);
        contentValues.put("password",password);
        contentValues.put("gender",gender);
        contentValues.put("dateofbirth",dateofbirth);

        long result = sqLiteDatabase.insert("user",null,contentValues);

        if (result == -1){
            return false;
        }
        else {
            return true;
        }
    }
    //Check registered phone number ?
    public Boolean Checkphonenumber(String phonenumber){
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();

        @SuppressLint("Recycle") Cursor cursor = sqLiteDatabase.rawQuery("SELECT * FROM user WHERE phonenumber = ?",new String[]{phonenumber});

        if (cursor.getCount() > 0)
        {
            return true;
        }
        else {
            return false;
        }

    }
    //Check if the account exists?
    public Boolean CheckAccount(String phonenumber,String password){
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();

        @SuppressLint("Recycle") Cursor cursor = sqLiteDatabase.rawQuery("SELECT * FROM user WHERE phonenumber = ? AND password = ?",new String[]{phonenumber,password});

        if (cursor.getCount() > 0)
        {
            return true;
        }
        else return false;
    }
    // Get data user by phone number
    public ArrayList<UserModel> getLogin_User(String phonenumber){
        ArrayList <UserModel> listUser = new ArrayList<>();

        SQLiteDatabase sqLiteDatabase = this.getReadableDatabase();
        @SuppressLint("Recycle") Cursor cursor = sqLiteDatabase.rawQuery("SELECT * FROM user WHERE phonenumber = ?",new String[]{phonenumber});
        if (cursor.moveToFirst()){
            String username = cursor.getString(0);
            String gender = cursor.getString(3);
            String dateofbirth = cursor.getString(4);
            String phoneNumber = cursor.getString(1);
            String password = cursor.getString(2);

            UserModel userModel = new UserModel();
            userModel.setUsername(username);
            userModel.setGender(gender);
            userModel.setDateOfBirth(dateofbirth);
            userModel.setPhonenumber(phoneNumber);
            userModel.setPassword(password);

            listUser.add(userModel);
        }
        return listUser;
    }
    // Update data User
    public boolean updateDataUser(String old_phonenumber, String username, String gender, String dateofbirth,String new_phonenumber, String password) {
        // Get a writable instance of the SQLiteDatabase
        SQLiteDatabase db = this.getWritableDatabase();
        // Create a new ContentValues object and put the new values
        ContentValues values = new ContentValues();
        values.put("username", username);
        values.put("gender", gender);
        values.put("phonenumber",new_phonenumber);
        values.put("dateofbirth", dateofbirth);
        values.put("password", password);
        // Define the where clause and where arguments
        String whereClause = "phonenumber = ?";
        String[] whereArgs = new String[]{old_phonenumber};
        // Update the row in the database
        int rowsAffected = db.update("user", values, whereClause, whereArgs);
        // Check if the update was successful
        return rowsAffected > 0;
    }

    //Insert Data to Table OrderlistFood
    public Boolean InsertDataToOrder(byte[] image, String foodName, String rating,int quantity, int price) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put("image", image);
        values.put("foodname", foodName);
        values.put("rating", rating);
        values.put("quantity", quantity);
        values.put("price", price);

        long result = db.insert("orderlist", null, values);

        if (result == -1){
            return false;
        }
        else {
            return true;
        }
    }
    //Get List Food Order
    public ArrayList<CartModel> getAllDataOrder() {
        ArrayList<CartModel> orderList = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM orderlist", null);
        if (cursor.moveToFirst()) {
            do {
                int id = cursor.getInt(0);
                byte[] image = cursor.getBlob(1);
                String foodName = cursor.getString(2);
                String rating = cursor.getString(3);
                int quantity = cursor.getInt(4);
                int price = cursor.getInt(5);
                CartModel order = new CartModel(id,image,foodName,rating,quantity,price);
                orderList.add(order);
            } while (cursor.moveToNext());
        }
        cursor.close();
        db.close();
        return orderList;
    }
    //Get Total Price
    public int getTotalPrice() {
        int totalPrice = 0;
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.rawQuery("SELECT SUM(price*quantity) FROM orderlist", null);
        if (cursor.moveToFirst()) {
            totalPrice = cursor.getInt(0);
        }
        cursor.close();
        db.close();

        return totalPrice;
    }
    //Delete Food by Name
    public void DeleteFoodByName(String foodName) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete("orderlist", "foodname = ?", new String[]{foodName});
        db.close();
    }

   //Delete All List Order
    public void DeleteCartOrder(){
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL("DELETE FROM orderlist");
    }

    //Update quantity and total price
    public void updateQuantity(int id, int quantity) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put("quantity", quantity);

        db.update("orderlist", values, "id = ?", new String[]{String.valueOf(id)});
        db.close();
    }

    //Insert Data to Table historyOrder
    public Boolean insertDataBill(String username, String phonenumber, String address, int price) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();

        contentValues.put("username", username);
        contentValues.put("phonenumber", phonenumber);
        contentValues.put("address", address);
        contentValues.put("price", price);
        @SuppressLint("SimpleDateFormat") SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
        String currentDate = sdf.format(new Date());
        contentValues.put("date", currentDate);

        long result = db.insert("listBill", null, contentValues);

        if (result == -1){
            return false;
        }
        else {
            return true;
        }
    }
    //Get List Bill
    public ArrayList<BillModel> getAllDataBill() {
        ArrayList<BillModel> historyOrders = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query("listBill", null, null, null, null, null, null);
        while (cursor.moveToNext()) {
            int id = cursor.getInt(0);
            String username = cursor.getString(1);
            String phonenumber = cursor.getString(2);
            String address = cursor.getString(3);
            int price = cursor.getInt(4);
            String date = cursor.getString(5);

            historyOrders.add(new BillModel(id, username, phonenumber, address,date, price));
        }
        cursor.close();
        return historyOrders;
    }
    //Delete All Bill
    public void DeleteAllBill(){
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL("DELETE FROM listBill");
    }
}

