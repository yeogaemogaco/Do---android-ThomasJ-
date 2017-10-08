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


public class FetchData extends AsyncTask<String, Void,String> {
    String json_url = "http://ruddmsdl000.cafe24.com/groupFromDb.php";
    String data="";
    String dataParsed="";
    String singleParsed="";
    String idParsed = "";
    Page1Activity p;
    String groupid = "";
    String[] colors;
    Page1Activity pageView;
    MainActivity m;
    JSONObject JsonObject;
    public static int colorsLength;
    @Override
    protected void onPreExecute() {
        super.onPreExecute();
    }
  @Override
    protected String doInBackground(String... params) {
        URL url = null;
        String s= params[0];
        try {
            url = new URL(json_url+s);
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
            for(int i=0;i<JsonArray.length();i++){
                JsonObject = JsonArray.getJSONObject(i);
                this.colors = pageView.toStringArray(JsonArray);
                singleParsed = (String) JsonObject.get("name");
                    pageView.colors[i] = singleParsed;
                    dataParsed = dataParsed + singleParsed;

            }
            colorsLength = JsonArray.length();


        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return null;
    }


    @Override
    protected void onPostExecute(String s) {
        super.onPreExecute();
        //TodoActivity.test.setText(this.dataParsed);
       // Page1Activity.colors = this.colors;

    }



}