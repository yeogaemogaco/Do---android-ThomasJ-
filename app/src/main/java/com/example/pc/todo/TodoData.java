package com.example.pc.todo;

import android.os.AsyncTask;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class TodoData extends AsyncTask<String, Void,String> {
    String json_url = "http://ruddmsdl000.cafe24.com/todoFromDb.php";
    String data = "";
    String dataParsed = "";
    String singleParsed = "";
    String idParsed = "";
    TodoActivity todoActivity;
    String[] tasks;
    String doDoNot="";
    Page1Activity pageView;
    MainActivity m;
    JSONObject JsonObject;
    public static int tasksLength;
    @Override
    protected void onPreExecute() {
        super.onPreExecute();
    }

    @Override
    protected String doInBackground(String... params) {
        URL url = null;
        String s = params[0];
        try {
            url = new URL(json_url+s);
            Log.d("json_url",json_url);
            Log.d("SSSSS",s);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            InputStream inputStream = connection.getInputStream();
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
            StringBuffer sb = new StringBuffer();
            String line = "";
            while ((line) != null) {
                line = bufferedReader.readLine();
                data = data + line;
            }
            JSONArray JsonArray = new JSONObject(data).getJSONArray("result");
            Log.d("JSON", String.valueOf(JsonArray));
            for (int i = 0; i < JsonArray.length(); i++) {
                JsonObject = JsonArray.getJSONObject(i);
                this.tasks = toStringArray(JsonArray);
                singleParsed = (String) JsonObject.get("name");
                doDoNot = (String) JsonObject.get("doDoNot");
                todoActivity.tasks[i] = singleParsed;
                todoActivity.doDoNot[i] = doDoNot;


            }
            tasksLength = JsonArray.length();


        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return null;
    }

    public static String[] toStringArray(JSONArray array) {
        if(array==null)
            return null;

        String[] arr=new String[array.length()];
        for(int i=0; i<arr.length; i++) {
            arr[i]=array.optString(i);
        }
        return arr;
    }
}

