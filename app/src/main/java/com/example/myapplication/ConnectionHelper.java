package com.example.myapplication;

import android.os.StrictMode;
import android.util.Log;

import java.sql.Connection;
import java.sql.DriverManager;

public class ConnectionHelper {

    String uname, pass, ip, port, database;
    String ConnectionURL="";
    Connection connection=null;

    public Connection ConnectionClass(){

        ip="185.78.85.187";
        database="DigitalSignage";
        uname="sa";
        pass="20Reklamoid.19";
        port="20433";

        StrictMode.ThreadPolicy policy=new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);


        try{

            Class.forName("net.sourceforge.jtds.jdbc.Driver");
            ConnectionURL= "jdbc:jtds:sqlserver://"+ ip + ":"+ port+";"+"databasename="+ database+";user="+uname+";password="+pass+";";
            connection= DriverManager.getConnection(ConnectionURL);
        }
        catch(Exception ex){
            Log.e("Error", ex.getMessage());
        }
        return connection;

    }

}