package com.example.tinyhouse.ui.Customer;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

public class ClientHire {
    private int ID;
    private String Name;
    private String Address;
    private String ContactNo;
    private String FloorType;
    private String Bedroom;
    private String Bathroom;
    private String Date;
    private String Location;
    private byte[] image;
    private String Email;
    private String CleanerEmail;
    private String CleanerUsername;
//    private String Review;
//   private int Price;

    public ClientHire(){

    }
    //set


//    public void setReview(String review) {Review = review;}

    public void setCleanerUsername(String cleanerUsername) {CleanerUsername = cleanerUsername;}

    public void setCleanerEmail(String cleanerEmail) {CleanerEmail = cleanerEmail;}

    public void setEmail(String email) {Email = email;}

    public void setID(int ID) {this.ID = ID;}

    public void setName(String name) {Name = name;}

    public void setAddress(String address) {Address = address;}

    public void setContactNo(String contactNo) {ContactNo = contactNo;}

    public void setFloorType(String floorType) {FloorType = floorType;}

    public void setBedroom(String bedroom) {Bedroom = bedroom;}

    public void setBathroom(String bathroom) {Bathroom = bathroom;}

    public void setDate(String date) {Date = date;}

    public void setLocation(String location) {Location = location;}

    public void setImage(byte[] image) {this.image = image;}

//    public void setPrice(int price) {Price = price;}

    //get


//   public String getReview() {return Review;}

    public String getCleanerUsername() {return CleanerUsername;}

    public String getCleanerEmail() {
        return CleanerEmail;
    }

    public String getEmail() {
        return Email;
    }

    public int getID() {
        return ID;
    }

    public String getName() {
        return Name;
    }

    public String getAddress() {
        return Address;
    }

    public String getContactNo() {
        return ContactNo;
    }

    public String getFloorType() {
        return FloorType;
    }

    public String getBedroom() {
        return Bedroom;
    }

    public String getBathroom() {
        return Bathroom;
    }

    public String getDate() {
        return Date;
    }

    public String getLocation() {
        return Location;
    }

    public byte[] getImage() {
        return image;
    }

//    public int getPrice() {return Price;}

    //saving to db
    public void save(SQLiteDatabase db){
        try{
            ContentValues values = new ContentValues();
            values.put("Name",Name);
            values.put("Address",Address);
            values.put("ContactNo",ContactNo);
            values.put("FloorType",FloorType);
            values.put("Bedroom",Bedroom);
            values.put("Bathroom",Bathroom);
            values.put("Date",Date);
            values.put("Location",Location);
            values.put("image",image);
            values.put("email",Email);
            values.put("CleanerEmail",CleanerEmail);
            values.put("CleanerUsername",CleanerUsername);
//            values.put("Review",Review);
//            values.put("price",image);
            db.insert("Post",null, values);

        }
        catch(Exception ex){
            throw ex;
        }
    }
    //getting data to array
    public List<ClientHire> GetPost(SQLiteDatabase db,String Email){
        try {
            List<ClientHire> hireList = new ArrayList<>();
            String mail=Email;
            String query = "select id,Name,Address, ContactNo, FloorType,Bedroom,Bathroom,Date,Location,Image,Email, CleanerEmail,CleanerUsername from Post where email='"+mail+"'";
            Cursor cursor = db.rawQuery(query, null);
            if (cursor.moveToFirst()) {

                do {
                    if (cursor.getString(10).equals(mail) ) {
                        ClientHire hire = new ClientHire();
                        hire.setID(cursor.getInt(0));
                        hire.setName(cursor.getString(1));
                        hire.setAddress(cursor.getString(2));
                        hire.setContactNo(cursor.getString(3));
                        hire.setFloorType(cursor.getString(4));
                        hire.setBedroom(cursor.getString(5));
                        hire.setBathroom(cursor.getString(6));
                        hire.setDate(cursor.getString(7));
                        hire.setLocation(cursor.getString(8));
                        hire.setImage(cursor.getBlob(9));
                        hire.setEmail(cursor.getString(10));
                        hire.setCleanerEmail(cursor.getString(11));
                        hire.setCleanerUsername(cursor.getString(12));
//                        hire.setReview(cursor.getString(13));

                        hireList.add(hire);
                    }

                }
                while (cursor.moveToNext());
            }
            return hireList;
        }
        catch(Exception ex){
            throw ex;
        }
    }

    public List<ClientHire> GetHire(SQLiteDatabase db){
        try {
            List<ClientHire> hireList = new ArrayList<>();
            String query = "select id,Name,Address, ContactNo, FloorType,Bedroom,Bathroom,Date,Location,Image,Email,CleanerEmail,CleanerUsername from Post ";
            Cursor cursor = db.rawQuery(query, null);
            if (cursor.moveToFirst()) {

                do {

                        ClientHire hire = new ClientHire();
                        hire.setID(cursor.getInt(0));
                        hire.setName(cursor.getString(1));
                        hire.setAddress(cursor.getString(2));
                        hire.setContactNo(cursor.getString(3));
                        hire.setFloorType(cursor.getString(4));
                        hire.setBedroom(cursor.getString(5));
                        hire.setBathroom(cursor.getString(6));
                        hire.setDate(cursor.getString(7));
                        hire.setLocation(cursor.getString(8));
                        hire.setImage(cursor.getBlob(9));
                        hire.setEmail(cursor.getString(10));
                        hire.setCleanerEmail(cursor.getString(11));
                        hire.setCleanerUsername(cursor.getString(12));
//                        hire.setReview(cursor.getString(13));

                        hireList.add(hire);


                }
                while (cursor.moveToNext());
            }
            return hireList;
        }
        catch(Exception ex){
            throw ex;
        }
    }

    //delete from db
    public void Delete(SQLiteDatabase db){
        try{
            db.delete("Post","id="+ID,null);

        }
        catch(Exception ex){
            throw ex;
        }
    }

    //update db
    public void Update(SQLiteDatabase db){
        try{
            ContentValues values = new ContentValues();
            values.put("Name",Name);
            values.put("Address",Address);
            values.put("ContactNo",ContactNo);
            values.put("FloorType",FloorType);
            values.put("Bedroom",Bedroom);
            values.put("Bathroom",Bathroom);
            values.put("Date",Date);
            values.put("Location",Location);
            values.put("image",image);
            values.put("email",Email);
            values.put("CleanerEmail",CleanerEmail);
            values.put("CleanerUsername",CleanerUsername);
//           values.put("Review",Review);
//            values.put("price",Price);
            db.update("Post",values,"id="+ID,null);

        }
        catch(Exception ex){
            throw ex;
        }
    }


    public void UpdateCleanerEmail(SQLiteDatabase db,String CM,String E){
        try{
            ContentValues values = new ContentValues();
            values.put("CleanerEmail",CM);


            db.update("Post",values,"ID="+E,null);

        }
        catch(Exception ex){
            throw ex;
        }
    }

    public List<ClientHire> GetMyCleaner(SQLiteDatabase db,String Email){
        try {
            List<ClientHire> hireList = new ArrayList<>();
            String CE="";
            String query = "select id,Name,Address, ContactNo, FloorType,Bedroom,Bathroom,Date,Location,Image,Email, CleanerEmail,CleanerUsername from Post where email='"+Email+"'";
            Cursor cursor = db.rawQuery(query, null);
            if (cursor.moveToFirst()) {

                do {
                    if (cursor.getString(11)!=CE ) {
                        ClientHire hire = new ClientHire();
                        hire.setID(cursor.getInt(0));
                        hire.setName(cursor.getString(1));
                        hire.setAddress(cursor.getString(2));
                        hire.setContactNo(cursor.getString(3));
                        hire.setFloorType(cursor.getString(4));
                        hire.setBedroom(cursor.getString(5));
                        hire.setBathroom(cursor.getString(6));
                        hire.setDate(cursor.getString(7));
                        hire.setLocation(cursor.getString(8));
                        hire.setImage(cursor.getBlob(9));
                        hire.setEmail(cursor.getString(10));
                        hire.setCleanerEmail(cursor.getString(11));
                        hire.setCleanerUsername(cursor.getString(12));
//                        hire.setReview(cursor.getString(13));

                        hireList.add(hire);
                    }

                }
                while (cursor.moveToNext());
            }
            return hireList;
        }
        catch(Exception ex){
            throw ex;
        }
    }
    public void UpdateCleanerUsername(SQLiteDatabase db,String CName,String U){
        try{
            ContentValues values = new ContentValues();
            values.put("CleanerUsername",CName);


            db.update("Post",values,"ID="+U,null);

        }
        catch(Exception ex){
            throw ex;
        }
    }



}

