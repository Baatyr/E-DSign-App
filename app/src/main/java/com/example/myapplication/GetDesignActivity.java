package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;

import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;

import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Toast;

import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.example.myapplication.databinding.ActivityGetDesignBinding;

public class GetDesignActivity extends AppCompatActivity {

    private AppBarConfiguration appBarConfiguration;
    private ActivityGetDesignBinding binding;
    private WebView webview;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityGetDesignBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        Intent intent = getIntent();
         String screenID =  getIntent().getExtras().get("id").toString();
        String url="https://bahadirunal.com/PharmacyPlay/GetPlaylist?screenId="+screenID;
        Toast.makeText(this, url, Toast.LENGTH_LONG).show();
        webview=findViewById(R.id.webView);
        webview.setWebViewClient(new WebViewClient());
        webview.loadUrl(url);

    }
    @Override
    public void onBackPressed(){
        if(webview.canGoBack()) {
            webview.goBack();

        }
        else{
            super.onBackPressed();
        }
    }


}