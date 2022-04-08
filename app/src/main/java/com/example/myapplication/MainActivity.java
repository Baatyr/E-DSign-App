package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.app.LauncherActivity;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.util.Log;
import android.view.View;

import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class MainActivity extends AppCompatActivity {

    int ScreenID;
    Connection connect;
    String ConnectionResult = "";
    String email;
    Boolean isSuccess;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        ListView listView = (ListView) findViewById(R.id.screenList);
        TextView textView = (TextView) findViewById(R.id.VirtualScreenID);


        Intent intent = getIntent();
        email = getIntent().getExtras().get("email").toString();

        getScreenList(email);



    }

    public void getDesigns(String screenID) {

        Intent intent = new Intent(this, GetDesignActivity.class);

        intent.putExtra("id", screenID); //Optional parameters

        startActivity(intent);
    }

    public void Login(View view) {

        Intent intent = new Intent(this, LoginActivity.class);


        startActivity(intent);
    }

    public void getScreenList(String email) {
        try {
            ConnectionHelper connectionHelper = new ConnectionHelper();
            connect = connectionHelper.ConnectionClass();

            if (connect != null) {

                String query = "Select UserID from Users Where Email='" + email + "'";

                Statement st = connect.createStatement();
                ResultSet resultSet = st.executeQuery(query);
                while (resultSet.next()) {
                    String userID = resultSet.getString(1);
                    Toast.makeText(this, userID, Toast.LENGTH_LONG).show();
                    getList(userID);


                }
            }


        } catch (Exception ex) {
            Log.e("error", ex.getMessage());
        }


    }


    public List<Map<String, String>> getList(String userID) {

        List<Map<String, String>> data = null;

        data = new ArrayList<Map<String, String>>();
        List<String> list = new ArrayList<String>();
        try {
            ConnectionHelper connectionHelper = new ConnectionHelper();
            connect = connectionHelper.ConnectionClass();
            if (connect != null) {

                String _query = "SELECT VirtualScreenID FROM PlaylistScreens WHERE UserID=" + userID;
                Statement st = connect.createStatement();
                ResultSet resultSet = st.executeQuery(_query);
                while (resultSet.next()) {
                    Map<String, String> dtname = new HashMap<String, String>();
                    dtname.put("VirtualScreenID", resultSet.getString("VirtualScreenID"));
                    list.add(resultSet.getString("VirtualScreenID"));
                    final ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                            android.R.layout.simple_list_item_1, android.R.id.text1, list
                    );
                    ListView listView = (ListView) findViewById(R.id.screenList);
                    listView.setAdapter(adapter);
                    listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                        public void onItemClick(AdapterView<?> parent, View view,
                                                int position, long id) {

                       String screenID= (String) ((TextView) view).getText();
                       getDesigns(screenID);
                        }
                    });

                }
                ConnectionResult = "Success";
                isSuccess = true;
                connect.close();
            } else {
                ConnectionResult = "failed";

            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return data;


    }


}

