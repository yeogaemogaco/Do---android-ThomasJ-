package com.example.pc.todo;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.util.Log;
import android.widget.TextView;

import static android.app.PendingIntent.getActivity;

/**
 * Created by ola on 14/09/2017.
 */

public class SharedPreferenceManager {
    public static final String LOGIN_URL = "http://ruddmsdl000.cafe24.com/Login.php";
    public static final String KEY_EMAIL = "email";
    public static final String KEY_PASSWORD = "password";
    public static final String LOGIN_SUCCESS = "success";
    public static final String SHARED_PREF_NAME = "tech";
    public static String EMAIL_SHARED_PRET;
    public static final String LOGGEDIN_SHARED_PREF = "loggedin";
    public SharedPreferences sharedPreferences;

    private static SharedPreferenceManager mInstance;
    private static Context mContext;

    private SharedPreferenceManager(Context context) {

        this.mContext = context;

    }

    public static synchronized SharedPreferenceManager getInstance(Context context) {
        if (mInstance == null) {
            mInstance = new SharedPreferenceManager(context);
        }
        return mInstance;
    }

    public void logout() {
        SharedPreferences sharedPreferences = mContext.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.clear();
        editor.apply();
        mContext.startActivity(new Intent(mContext, MainActivity.class));
    }

    /*public static String saveLoginDetails() {
        SharedPreferences sharedPreferences = mContext.getSharedPreferences("LoginDetails", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("Email", email);
        Log.d("email",email);
       // EMAIL_SHARED_PRET = email;
        //Log.d("****",EMAIL_SHARED_PRET);
        //editor.commit();
    }
*/
}
