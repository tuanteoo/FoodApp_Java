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

    public DataBaseHandler(@Nullable Context context) {
        super(context, "foodapp.db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE IF NOT EXISTS user(username TEXT,gender TEXT,dateofbirth TEXT, phonenumber TEXT PRIMARY KEY,password TEXT)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS user");
        onCreate(db);
    }

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

    public Boolean Checkphonenumber(String phonenumber){
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();

        @SuppressLint("Recycle") Cursor cursor = sqLiteDatabase.rawQuery("SELECT * FROM user WHERE phonenumber = ?",new String[]{phonenumber});

        if (cursor.getCount() > 0){
            return true;
        }else {
            return false;
        }
    }

    public Boolean CheckAccount(String phonenumber,String password){
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();

        @SuppressLint("Recycle") Cursor cursor = sqLiteDatabase.rawQuery("SELECT * FROM user WHERE phonenumber = ? AND password = ?",new String[]{phonenumber,password});

        if (cursor.getCount() > 0){
            return true;
        }else {
            return false;
        }
    }

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
}
