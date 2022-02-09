package com.mahirshadid.vax;

import android.os.Bundle;
import android.view.KeyEvent;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import androidx.appcompat.app.AppCompatActivity;

public class kalerkantho extends AppCompatActivity {

    WebView web;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_kalerkantho);

        web=findViewById(R.id.webview);
        WebSettings webSettings=web.getSettings();
        webSettings.setJavaScriptEnabled(true);

        web.setWebViewClient(new kalerkantho.Callback());
        web.loadUrl("https://www.kalerkantho.com/");
    }

    private class Callback extends WebViewClient {
        @Override
        public boolean shouldOverrideKeyEvent(WebView view, KeyEvent event) {
            return false;
        }
    }
}