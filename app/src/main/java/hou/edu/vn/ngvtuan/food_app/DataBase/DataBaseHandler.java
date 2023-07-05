package hou.edu.vn.ngvtuan.food_app.DataBase;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.util.ArrayList;

import hou.edu.vn.ngvtuan.food_app.models.UserModel;

public class DataBaseHandler extends SQLiteOpenHelper {
    private Context context;

    public DataBaseHandler(@Nullable Context context) {
        super(context, "foodapp.db", null, 1);
        this.context = context;
    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE IF NOT EXISTS user(username TEXT,gender TEXT,dateofbirth TEXT, phonenumber TEXT PRIMARY KEY,password TEXT)");
        db.execSQL("CREATE TABLE IF NOT EXISTS food(image BLOB,foodname TEXT PRIMARY KEY,rating TEXT, price INT)");
        db.execSQL("CREATE TABLE IF NOT EXISTS orderlist(image BLOB,foodname TEXT PRIMARY KEY,rating TEXT,price INT)");
    }
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS user");
        db.execSQL("DROP TABLE IF EXISTS food");
        db.execSQL("DROP TABLE IF EXISTS orderlist");
        onCreate(db);
    }


    //Insert Data to Table OrderlistFood
//    public void InsertDataToOrder(int imageResId, String foodName, String rating, Integer price) {
//        // Convert the resource ID into a Bitmap
//        Bitmap image = BitmapFactory.decodeResource(context.getResources(), imageResId);
//
//        // Convert the Bitmap into a byte[] array
//        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
//        image.compress(Bitmap.CompressFormat.PNG, 100, outputStream);
//        byte[] imageData = outputStream.toByteArray();
//
//        // Insert the data into the database
//        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
//        ContentValues contentValues = new ContentValues();
//        contentValues.put("image", imageData);
//        contentValues.put("nameFood", foodName);
//        contentValues.put("rating", rating);
//        contentValues.put("price", price);
//        sqLiteDatabase.insert("orderlist", null, contentValues);
//    }

//    public List<CartModel> getOrderListFood() {
//        List<CartModel> cartModelList = new ArrayList<>();
//        SQLiteDatabase sqLiteDatabase = this.getReadableDatabase();
//        Cursor cursor = sqLiteDatabase.rawQuery("SELECT * FROM orderlist", null);
//
//        while (cursor.moveToNext()) {
//            @SuppressLint("Range") byte[] image = cursor.getBlob(cursor.getColumnIndex("image"));
//            @SuppressLint("Range") String foodName = cursor.getString(cursor.getColumnIndex("nameFood"));
//            @SuppressLint("Range") String rating = cursor.getString(cursor.getColumnIndex("rating"));
//            @SuppressLint("Range") int price = cursor.getInt(cursor.getColumnIndex("price"));
//
//            CartModel cartModel = new CartModel(image, foodName, rating, price);
//            cartModelList.add(cartModel);
//        }
//        cursor.close();
//        return cartModelList;
//    }
//
//
//    public int getTotalPrice() {
//        int totalPrice = 0;
//        SQLiteDatabase db = this.getReadableDatabase();
//
//        Cursor cursor = db.rawQuery("SELECT SUM(price) FROM orderlist", null);
//        if (cursor.moveToFirst()) {
//            totalPrice = cursor.getInt(3);
//        }
//        cursor.close();
//        db.close();
//
//        return totalPrice;
//    }
    //Insert Data To Table User
    public Boolean InsertData (String username,String gender,String dateofbirth,String phonenumber,String password){
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();

        contentValues.put("username",username);
        contentValues.put("gender",gender);
        contentValues.put("dateofbirth",dateofbirth);
        contentValues.put("phonenumber",phonenumber);
        contentValues.put("password",password);

        long result = sqLiteDatabase.insert("user",null,contentValues);

        if (result == -1){
            return false;
        }else {
            return true;
        }
    }
    //Check registered phone number ?
    public Boolean Checkphonenumber(String phonenumber){
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();

        @SuppressLint("Recycle") Cursor cursor = sqLiteDatabase.rawQuery("SELECT * FROM user WHERE phonenumber = ?",new String[]{phonenumber});

        if (cursor.getCount() > 0){
            return true;
        }else {
            return false;
        }
    }
    //Check if the account exists?
    public Boolean CheckAccount(String phonenumber,String password){
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();

        @SuppressLint("Recycle") Cursor cursor = sqLiteDatabase.rawQuery("SELECT * FROM user WHERE phonenumber = ? AND password = ?",new String[]{phonenumber,password});

        if (cursor.getCount() > 0){
            return true;
        }else {
            return false;
        }
    }
    // Lấy thông tin User với Phonenumber
    public ArrayList<UserModel> getLogin_User(String phonenumber){
        ArrayList <UserModel> listUser = new ArrayList<>();

        SQLiteDatabase sqLiteDatabase = this.getReadableDatabase();
         @SuppressLint("Recycle") Cursor cursor = sqLiteDatabase.rawQuery("SELECT * FROM user WHERE phonenumber = ?",new String[]{phonenumber});

         if (cursor.moveToFirst()){
            String username = cursor.getString(0);
            String gender = cursor.getString(1);
            String dateofbirth = cursor.getString(2);
            String phoneNumber = cursor.getString(3);
            String password = cursor.getString(4);

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

    // Update thông tin User
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
}

