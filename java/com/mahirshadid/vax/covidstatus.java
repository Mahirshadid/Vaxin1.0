package com.mahirshadid.vax;

import android.os.Bundle;
import android.view.KeyEvent;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import androidx.appcompat.app.AppCompatActivity;

public class covidstatus extends AppCompatActivity {

    WebView web;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_covidstatus);

        web=findViewById(R.id.webview);
        WebSettings webSettings=web.getSettings();
        webSettings.setJavaScriptEnabled(true);

        web.setWebViewClient(new Callback());
        web.loadUrl("https://www.corona.gov.bd");
    }

    private class Callback extends WebViewClient {
        @Override
        public boolean shouldOverrideKeyEvent(WebView view, KeyEvent event) {
            return false;
        }
    }
}