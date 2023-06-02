package com.example.tinyhouse;

import android.content.Context;
import android.content.SharedPreferences;

public class SharedPreference {
    public static final String APP_NAME ="TINY_HOUSE";
    public  static final String KEY_USER_NAME ="USER_NAME";
    public  static final String  KEY_STATUS ="STATUS";
    public  static  final String USER_TYPE="USER_TYPE";
    public  static  final String USER_EMAIL="USER_EMAIL";



    public SharedPreference(){
        super();
    }
    public void SaveString(Context context,String value,String key){

        SharedPreferences preference = context.getSharedPreferences(APP_NAME,Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = preference.edit();
        editor.putString(key, value);
        editor.commit();

    }
    public  void SaveBool(Context context,Boolean value,String key){
        SharedPreferences preference = context.getSharedPreferences(APP_NAME,Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = preference.edit();
        editor.putBoolean(key,value);
        editor.commit();

    }
    public String GetString(Context context,String key){
        SharedPreferences preference = context.getSharedPreferences(APP_NAME,Context.MODE_PRIVATE);
        return preference.getString(key,null);
    }
    public  boolean GetBoolean(Context context,String key){
        SharedPreferences preference = context.getSharedPreferences(APP_NAME,Context.MODE_PRIVATE);
        return preference.getBoolean(key,false);
    }
}
