package cis657.project.hymnsingapp;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.content.Context;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.JavascriptInterface;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLDecoder;
import java.net.URLEncoder;

public class PdfScreen extends AppCompatActivity {

    String url = "https://hymnary.org";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pdf_screen);
        String s = getIntent().getStringExtra("n1");
        url = url + s;
        System.out.println("URL: "+url);
        WebView wv = (WebView) findViewById(R.id.the_webview);
        wv.getSettings().setJavaScriptEnabled(true);
//            wv.getSettings().setLoadWithOverviewMode(true);
//            wv.getSettings().setUseWideViewPort(true);
//            wv.setWebViewClient(new WebViewClient() {
//                @Override
//                public boolean shouldOverrideUrlLoading(WebView view, String url1) {
//                    wv.loadUrl("http://docs.google.com/viewer?url=" + url);
//                    return true;
//                }
//            });;

        wv.setWebViewClient(new WebViewClient() {
            public void onReceivedError(WebView view, int errorCode, String description, String   failingUrl) {

            }
        });
        wv.loadUrl("http://docs.google.com/viewer?url=" + url);



        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);



//        FloatingActionButton fab = findViewById(R.id.fab);
//        fab.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
//                        .setAction("Action", null).show();
//            }
//        });
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }



}