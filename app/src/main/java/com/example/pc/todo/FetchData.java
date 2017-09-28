package com.example.pc.todo;

import android.os.AsyncTask;
import android.util.Log;
import android.widget.TextView;

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

/**
 * Created by ola on 28/09/2017.
 */


public class FetchData extends AsyncTask<Void, Void,String> {
    String json_url = "http://ruddmsdl000.cafe24.com/groupFromDb.php";
    String data="";
    String dataParsed="";
    String singleParsed="";
    Page1Activity p;
    String[] colors;
    Page1Activity pageView;
    @Override
    protected void onPreExecute() {
        super.onPreExecute();
      pageView = new Page1Activity();

    }

    @Override
    protected String doInBackground(Void... voids) {
        URL url = null;

        StringBuilder stringBuilder = null;
        try {
            url = new URL(json_url);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            InputStream inputStream = connection.getInputStream();
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
            StringBuffer sb = new StringBuffer();
            String line = "";
            //stringBuilder = new StringBuilder();
            while ((line) != null) {
                line = bufferedReader.readLine();
                data = data + line;
            }
            JSONArray JsonArray = new JSONObject(data).getJSONArray("result");
            String email = p.getEmail;
            int count = 0;
            for(int i=0;i<JsonArray.length();i++){
                JSONObject JsonObject = JsonArray.getJSONObject(i);
                String fcreate = (String) JsonObject.get("fcreate");
                this.colors = pageView.toStringArray(JsonArray);
                if(email.equals(fcreate)) {
                    singleParsed = (String) JsonObject.get("name");
                    pageView.colors[i] = singleParsed;
                    Log.d("****",pageView.colors[i]);
                    dataParsed = dataParsed + singleParsed;
                }
                else {

                    continue;
                }
                //pageView.colors =this.colors ;

            }
            //pageView.colorsLength = colors.length;


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
    protected void onPostExecute(String result) {
        super.onPostExecute(result);
        //TodoActivity.test.setText(this.dataParsed);

       // Page1Activity.colors = this.colors;

    }



}