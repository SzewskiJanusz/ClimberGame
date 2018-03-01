package com.janusz.climbergame.shared.scoreclient;

import java.io.IOException;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class NetClientPost
{

    public boolean addScoreToServer(String score, String name) throws IOException
    {
        try {

            URL url = new URL("http://192.168.1.19:8080/highscores");

            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setConnectTimeout(5000);
            conn.setDoOutput(true);
            conn.setRequestMethod("POST");
            conn.setRequestProperty("Content-Type", "application/json");

            String input = "{\"score\":\""+score+"\",\"name\":\""+name+"\"}";

            OutputStream os = conn.getOutputStream();
            os.write(input.getBytes());
            os.flush();
            int responseCode = conn.getResponseCode();
            if (responseCode >= 200 && responseCode < 300)
            {
                return true;
            }
            else{
                return false;
            }

        } catch (MalformedURLException e) {
            return false;
        }
    }
}
