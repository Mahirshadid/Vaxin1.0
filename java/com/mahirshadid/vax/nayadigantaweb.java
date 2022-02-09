package com.mahirshadid.vax;

import android.os.Bundle;
import android.view.KeyEvent;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import androidx.appcompat.app.AppCompatActivity;

public class nayadigantaweb extends AppCompatActivity {

    WebView web;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nayadigantaweb);
        web=findViewById(R.id.webview);
        WebSettings webSettings=web.getSettings();
        webSettings.setJavaScriptEnabled(true);

        web.setWebViewClient(new Callback());
        web.loadUrl("https://www.dailynayadiganta.com/");
    }

    private class Callback extends WebViewClient {
        @Override
        public boolean shouldOverrideKeyEvent(WebView view, KeyEvent event) {
            return false;
        }
    }
}