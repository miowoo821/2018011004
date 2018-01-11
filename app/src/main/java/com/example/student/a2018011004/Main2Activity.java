package com.example.student.a2018011004;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.webkit.WebChromeClient;
import android.webkit.WebView;

public class Main2Activity extends AppCompatActivity {
WebView wv;//宣告瀏覽器的物件
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        Intent it=getIntent();//新增一個Itent父類別下的抓intent物件
        String link=it.getStringExtra("link");//以link為目標去抓資料
        Log.d("net","link"+link);

        wv=(WebView)findViewById(R.id.webview);
        wv.setWebChromeClient(new WebChromeClient());
        wv.getSettings().setJavaScriptEnabled(true);
        wv.loadUrl(link);

    }
}
