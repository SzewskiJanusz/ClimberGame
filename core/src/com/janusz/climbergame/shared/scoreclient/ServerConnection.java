package com.janusz.climbergame.shared.scoreclient;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

/**
 * Server connection handle
 * Created by Bartek on 2017-11-23.
 */

public class ServerConnection
{
    private String hostIP = "31.25.250.150";
    private int port = 6940;
    private int timeout = 3500; // ms
    private Socket socket;



    public List<String> getScoresFromServer()
    {
        try {
            final List<String> list = new ArrayList<String>();
            createSocket(hostIP);

            PrintWriter out = new PrintWriter(socket.getOutputStream());
            out.println("getScore");

            Thread readThr = new Thread(new Runnable() {
                BufferedReader bufReader =
                        new BufferedReader(new InputStreamReader(socket.getInputStream()));
                @Override
                public void run() {
                    String msg;
                    try {
                        for(int i=0; i<10; i++)
                        {
                            msg = bufReader.readLine();
                            list.add(msg);
                        }
                        bufReader.close();
                    } catch (IOException e) {

                    }
                }
            });
            readThr.start();
            out.flush();

            while(readThr.isAlive())
            {;}
            out.close();
            return list;
        }
        catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    private void createSocket(String host){
        try {
            socket = new Socket();
            socket.connect(new InetSocketAddress(host, port), timeout);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}