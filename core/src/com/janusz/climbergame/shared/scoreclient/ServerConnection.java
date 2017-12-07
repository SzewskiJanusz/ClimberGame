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
    private String hostIP = "192.168.1.19";
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
                        for(int i=0; i<5; i++)
                        {
                            msg = bufReader.readLine();
                            list.add(msg);
                        }
                        bufReader.close();
                    } catch (IOException e) {
                        e.printStackTrace();
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


    public void sendScoreToServer(String nick, int score){
        try {
            createSocket(hostIP);
            PrintWriter out = new PrintWriter(socket.getOutputStream());
            out.println("sendScore "+ nick + ":" + String.valueOf(score));
            out.close();
            socket.close();
        } catch (IOException e) {
            System.err.println("Can't send score. Check connection to server. " +
                    e.getMessage());
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
