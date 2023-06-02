package com.example.tinyhouse.ui.Login;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

public class User {
    private String name;
    private String password;
    private String email;
    private  String userType;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setUserType(String userType) {this.userType = userType;}

    public String getUserType() {return userType;}

    public void Save(SQLiteDatabase db){
        try{

            ContentValues values = new ContentValues();
            values.put("name",name);
            values.put("email",email);
            values.put("password",password);
            values.put("userType",userType);
            db.insert("User",null,values);


        }
        catch(Exception ex){
            throw  ex;
        }
    }
    public boolean CheckLogin(SQLiteDatabase db){
        try {
            String query = "select email,password,name,userType from user where email='"+email+"' and password='"+password+"'";
            Cursor cursor = db.rawQuery(query, null);
            if (cursor.moveToFirst()) {

                do {
                    if (cursor.getString(0).equals(email) && cursor.getString(1).equals(password)) {
                        return true;
                    }
                }

                while (cursor.moveToNext()) ;
            }
            return  false;
        }
        catch(Exception ex){
            throw  ex;
        }
    }

    public String getType(SQLiteDatabase db){
        try {

            String query = "select email ,userType from user where email='"+email+"'";
            Cursor cursor = db.rawQuery(query, null);
            if (cursor.moveToFirst()) {

                do {
                    if (cursor.getString(0).equals(email) ) {
                        String Type= cursor.getString(1);

                        return Type;
                    }
                }

                while (cursor.moveToNext()) ;
            }
            return  null;
        }
        catch(Exception ex){
            throw  ex;
        }
    }


}





