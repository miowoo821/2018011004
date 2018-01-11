package com.example.student.a2018011004;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.StringReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.util.ArrayList;

import javax.net.ssl.HttpsURLConnection;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

public class MainActivity extends AppCompatActivity {
    ListView lv;
    ArrayAdapter<String> adapter;
    MyHandler dataHandler;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        lv=(ListView)findViewById(R.id.listView);
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent it=new Intent(MainActivity.this,Main2Activity.class);//新增一個intent物件，參數是作用頁面即前往頁面
                it.putExtra("link",dataHandler.newitems.get(i).link);//根據i位置抓出dataHandler物件裡面links方法裡面的資料
                startActivity(it);//啟動
            }
        });//監聽lv物件中項目的點擊



    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.menu_reload:
                new Thread(){
                    @Override
                    public void run() {
                        super.run();
                        String str_url="https://www.mobile01.com/rss/news.xml";
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
                            Log.d("NET","GGGG"+str1+"GGGGG");//以Log顯示這個字串
                            //final MyHandler dataHandler=new MyHandler();//要給其他人用藥放到最上面，變成全區
                            dataHandler=new MyHandler();
                            //SAX是Simple API for XML的簡稱,在Android裡面提供對XML文件的解析接口方法
                            //在 Android SDK 開發環境跟 XML 有關的方法有 SAX 與 DOM 兩種.
                            SAXParserFactory spf=SAXParserFactory.newInstance();
                            SAXParser sp=spf.newSAXParser();
                            XMLReader xr=sp.getXMLReader();
                            xr.setContentHandler(dataHandler);//將剛剛那個自己的的class NEW出來後放進去
                            xr.parse(new InputSource(new StringReader(str1)));

                            is.close();
                            isr.close();
                            br.close();

                            runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    String data []=new String[dataHandler.newitems.size()];
                                    for (int i=0;i<data.length;i++){
                                        data[i]=dataHandler.newitems.get(i).title;
                                    }
                                      adapter=new ArrayAdapter<String>(MainActivity.this,
                                            android.R.layout.simple_list_item_1,data);//dataHandler是上面從類別MyHandler所NEW出來的物件，再把這個物件裡面的title抓出來
                                    lv.setAdapter(adapter);//把adapter丟進去lv
                                }
                            });


                        } catch (MalformedURLException e) {
                            e.printStackTrace();

                        } catch (IOException e) {
                            e.printStackTrace();
                        } catch (SAXException e) {
                            e.printStackTrace();
                        } catch (ParserConfigurationException e) {
                            e.printStackTrace();
                        }


                    }
                }.start();//把這個功能丟到其他執行緒，不要再主執行緒裡面，程式就可以同時執行
                break;
        }

        return super.onOptionsItemSelected(item);
    }

    public void click1(View v){

    }

}
