package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;

import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;

import android.text.Editable;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;


import androidx.navigation.ui.AppBarConfiguration;


import com.example.myapplication.databinding.ActivityLoginBinding;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

public class LoginActivity extends AppCompatActivity {


    private ActivityLoginBinding binding;
    Connection connect;

    private Editable userEmail = null;
    private Editable userPassword= null;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityLoginBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());


    }


    public void LoginCheck(View view){
        EditText email=(EditText) findViewById(R.id.email);
        EditText password=(EditText) findViewById(R.id.password);
        userEmail=email.getText();
        userPassword=password.getText();

        try{
            ConnectionHelper connectionHelper=new ConnectionHelper();
            connect=connectionHelper.ConnectionClass();

            if(connect!=null){

                String query="Select Count(Name) from Users Where Email='"+userEmail+"' and Password='"+userPassword+"'";

                Statement st= connect.createStatement();
                ResultSet resultSet=st.executeQuery(query);
                while(resultSet.next()){

                    int result = resultSet.getInt (1);
                    if(result==1){
                        Intent intent = new Intent(this, MainActivity.class);

                        intent.putExtra("email", userEmail); //Optional parameters

                        startActivity(intent);
                    }
                    else {
                        Toast.makeText(this, "Kullanıcı Bulunamadı", Toast.LENGTH_LONG).show();
                    }
                }
            }


        }
        catch(Exception ex){
        Log.e("error", ex.getMessage());
        }

    }
}