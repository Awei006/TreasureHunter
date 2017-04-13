package com.awei.treasurehunter;

import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;


class HttpManager {

    public static String getData(RequestPackage p){

        BufferedReader reader = null;
        String uri = p.getUri();

        try {
            URL url = new URL(uri);
            HttpURLConnection con = (HttpURLConnection)url.openConnection();
            con.setRequestMethod(p.getMethod());

            if(p.getMethod().equals("POST")){

                con.setRequestProperty("content-type","application/json");
                JSONObject json = new JSONObject(p.getParams());
                String params = json.toString();
                con.setDoOutput(true);
                OutputStreamWriter writer = new OutputStreamWriter(con.getOutputStream());
                writer.write(params);
                writer.flush();
            }

            StringBuilder sb = new StringBuilder();

            con.connect();
            reader = new BufferedReader(new InputStreamReader(con.getInputStream()));

            String line;
            while ((line = reader.readLine())!=null){
                sb.append(line);
            }
            return sb.toString();
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }finally {
            if(reader!=null){
                try {
                    reader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
