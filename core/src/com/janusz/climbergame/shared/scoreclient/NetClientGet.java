package com.janusz.climbergame.shared.scoreclient;

/**
 * Created by Janusz on 2018-02-26.
 */

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class NetClientGet
{

    public List<Score> getScoresFromServer()
    {
        List<Score> objectList = new ArrayList<Score>();
        try {

            URL url = new URL("http://192.168.1.19:8080/highscores/ingame");
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            conn.setRequestProperty("Accept", "application/json");

            if (conn.getResponseCode() != 200) {
                throw new RuntimeException("Failed : HTTP error code : "
                        + conn.getResponseCode());
            }

            BufferedReader br = new BufferedReader(new InputStreamReader(
                    (conn.getInputStream())));

            String JSONoutput;

            if ((JSONoutput = br.readLine()) != null)
            {
                objectList = getObjectsFromJSON(JSONoutput);
            }

            conn.disconnect();

        } catch (MalformedURLException e) {

            e.printStackTrace();

        } catch (IOException e) {

            e.printStackTrace();
        }
        return objectList;
    }

    private List<Score> getObjectsFromJSON(String json) throws IOException
    {
        List<Score> list = new ArrayList<Score>();
        boolean write = false;
        String onejsonstr = "";
        ObjectMapper mapper = new ObjectMapper();

        for(int i = 1 ; json.charAt(i) != ']' ; i++)
        {
            if (write)
            {
                Score s = mapper.readValue(onejsonstr, Score.class);
                list.add(s);
                onejsonstr = "";
                write = false;
            }
            else
            {
                onejsonstr += json.charAt(i);
            }


            if (json.charAt(i) == '}')
            {
                write = true;
            }
        }

        return list;
    }

}
