package com.example.student.a2018011004;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;

import javax.net.ssl.HttpsURLConnection;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void click1(View v){
            new Thread(){
                @Override
                public void run() {
                    super.run();
                    String str_url="http://rate.bot.com.tw/xrt?Lang=zh-TW";
                    URL url=null;
                    try {
                        url=new URL((str_url));
                        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                        conn.setRequestMethod("GET");
                        conn.connect();
                        InputStream is=conn.getInputStream();
                        InputStreamReader isr=new InputStreamReader(is);
                        BufferedReader br=new BufferedReader(isr);

                        StringBuilder sb=new StringBuilder();//建立一個省資源可以放很多字串的大字串
                        String str;//用來放入大字串的非常多的小字串

                        while((str=br.readLine())!=null){
                            //br所讀出的line丟入str
                            // /所丟入str的東西不適空的時候，執行迴圈
                            sb.append(str);//每次回圈丟一個進去sb，最後會是一個大字串
                        }

                        String str1=sb.toString();//把這個大字串物件轉成字串丟進str字串
                       // Log.d("NET","GGGG"+str1+"GGGGG");//以Log顯示這個字串

                        int index1=str1.indexOf("日圓 (JPY)");//從頭開始找第一個參數
                        int index2=str1.indexOf("本行現金賣出",index1);//從index1的位置開始往後找第一個參數
                        int index3=str1.indexOf(">",index2);
                        int index4=str1.indexOf("<",index3);
                        String data1=str1.substring(index3+1,index4);//從str1裡面擷取從第一個參數的位置到第二個參數的位置裡面的字串丟入data1
                        Log.d("NET", "index1:" + index1 + "index2:" + index2 + "index3:" + index3);
                        Log.d("NET", data1);

                        is.close();
                        isr.close();
                        br.close();

                    } catch (MalformedURLException e) {
                        e.printStackTrace();

                    } catch (IOException e) {
                        e.printStackTrace();
                    }


                }
            }.start();//把這個功能丟到其他執行緒，不要再主執行緒裡面，程式就可以同時執行
    }

}
