package com.example.timothykang.trainingpeaks;

import android.os.AsyncTask;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class FetchData extends AsyncTask<Void, Void, String> {
    HttpURLConnection urlConnection;
    String url;
    FetchDataCallbackInterface callbackInterface;

    public FetchData(String url, FetchDataCallbackInterface callbackInterface) {
        //retrieve string for url
        this.url = url;
        this.callbackInterface = callbackInterface;
    }

    @Override
    protected String doInBackground(Void... voids) {
        //stringbuilder to retrieve incoming data
        StringBuilder result = new StringBuilder();
        try {
            URL url = new URL(this.url);
            urlConnection = (HttpURLConnection) url.openConnection();
            InputStream in = new BufferedInputStream(urlConnection.getInputStream());
            BufferedReader reader = new BufferedReader(new InputStreamReader(in));
            String line;
            while ((line = reader.readLine()) != null) {
                result.append(line);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            //close connection
            if (urlConnection != null)
                urlConnection.disconnect();
        }
        //return data
        return result.toString();
    }

    @Override
    protected void onPostExecute(String result) {
        super.onPostExecute(result);
        // pass the result to the callback function
        this.callbackInterface.fetchDataCallback(result);
    }
}
