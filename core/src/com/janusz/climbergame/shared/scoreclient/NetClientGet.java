package com.janusz.climbergame.shared.scoreclient;

/**
 * Created by Janusz on 2018-02-26.
 */

import com.fasterxml.jackson.databind.ObjectMapper;
import com.janusz.climbergame.shared.DefComponents;
import com.janusz.climbergame.shared.Toast;

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
    public List<Score> getScoresFromServer() throws IOException
    {
        List<Score> objectList = new ArrayList<Score>();

        URL url = new URL("http://szewskijan.cal24.pl/read.php");
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod("GET");
        conn.setConnectTimeout(5000);

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

        return objectList;
    }



    private List<Score> getObjectsFromJSON(String json) throws IOException
    {
        List<Score> list = new ArrayList<Score>();
        String onejsonstr = "";
        boolean skipComma = false;
        ObjectMapper mapper = new ObjectMapper();

        for(int i = 1 ; json.charAt(i) != ']' ; i++)
        {
            if (skipComma)
            {
                skipComma = false;
                continue;
            }

            onejsonstr += json.charAt(i);

            if (json.charAt(i) == '}')
            {
                Score s = mapper.readValue(onejsonstr, Score.class);
                list.add(s);
                onejsonstr = "";
                skipComma = true;
            }
        }

        return list;
    }

}
