package com.example.tinyhouse.ui.Review;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.tinyhouse.ui.Customer.ClientHire;

import java.util.ArrayList;
import java.util.List;

public class Review {
    private int ID;
    private String UserName;
    private String EmailID;
    private String Review;
    private String UserNamefor;


    //get


    public String getUserNamefor() {return UserNamefor;}

    public int getID() {
        return ID;
    }

    public String getReview() {return Review;}

    public String getEmailID() {return EmailID;}

    public String getUserName() {return UserName;}

    //set


    public void setUserNamefor(String userNamefor) {UserNamefor = userNamefor;}

    public void setID(int ID) {
        this.ID = ID;
    }

    public void setReview(String review) {Review = review;}

    public void setEmailID(String emailID) {EmailID = emailID;}

    public void setUserName(String userName) {UserName = userName;}

    //saving to db
    public void save(SQLiteDatabase db){
        try{
            ContentValues values = new ContentValues();
            values.put("UserName",UserName);
            values.put("EmailID",EmailID);
            values.put("Review",Review);
            values.put("UserNamefor",UserNamefor);
            db.insert("Reviews",null, values);

        }
        catch(Exception ex){
            throw ex;
        }
    }

    //getting data to array
    public List<Review> GetReview(SQLiteDatabase db){
        try {
            List<Review> reviewList = new ArrayList<>();
            String query = "select id,UserName,EmailID,Review,UserNamefor from Reviews";
            Cursor cursor = db.rawQuery(query, null);
            if (cursor.moveToFirst()) {

                do {

                    Review review = new Review();
                    review.setID(cursor.getInt(0));
                    review.setUserName(cursor.getString(1));
                    review.setEmailID(cursor.getString(2));
                    review.setReview(cursor.getString(3));
                    review.setUserNamefor(cursor.getString(4));
                    reviewList.add(review);


                }
                while (cursor.moveToNext());
            }
            return reviewList;
        }
        catch(Exception ex){
            throw ex;
        }
    }

    //update db
    public void Update(SQLiteDatabase db){
        try{
            ContentValues values = new ContentValues();
            values.put("UserName",UserName);
            values.put("EmailID",EmailID);
            values.put("Review",Review);
            values.put("UserNamefor",UserNamefor);

            db.update("Reviews",values,"id="+ID,null);

        }
        catch(Exception ex){
            throw ex;
        }
    }

    public void UpdateUsername(SQLiteDatabase db,String CName,String U){
        try{
            ContentValues values = new ContentValues();
            values.put("UserName",CName);


            db.update("Reviews",values,"ID="+U,null);

        }
        catch(Exception ex){
            throw ex;
        }
    }

    public void UpdateEmailID(SQLiteDatabase db,String CName,String U){
        try{
            ContentValues values = new ContentValues();
            values.put("EmailID",CName);


            db.update("Reviews",values,"ID="+U,null);

        }
        catch(Exception ex){
            throw ex;
        }
    }


}
