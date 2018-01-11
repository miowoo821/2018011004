package com.example.student.a2018011004;

import android.util.Log;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import java.util.ArrayList;

/**
 * Created by Student on 2018/1/10.
 */

public class MyHandler extends DefaultHandler{
    boolean istitle=false;
    boolean isitem=false;
    boolean islink=false;//準備抓連結
    StringBuilder linkSB=new StringBuilder();//弄一個大字串物件給連結存放用

public ArrayList<String> titles=new ArrayList<String>();
public ArrayList<String> links=new ArrayList<>();//弄一個陣列物件給連結存放用

    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
        super.startElement(uri, localName, qName, attributes);
        if(qName.equals("title")){
            istitle=true;
        }
        if(qName.equals("item")){
            isitem=true;
        }

        if (qName.equals("link")){
            islink=true;
        }

    }

    @Override
    public void endElement(String uri, String localName, String qName) throws SAXException {
        super.endElement(uri, localName, qName);
       if(qName.equals("title")){
           istitle=false;
       }
        if(qName.equals("item")){
            isitem=false;
        }
        if(qName.equals("link")){
            islink=false;
            if(isitem){//在item等於true(原始碼有item標籤)的時候才執行放入link的動作
            links.add(linkSB.toString());
            linkSB=new StringBuilder();//清除linkSB的內容
            }
        }
    }

    @Override
    public void characters(char[] ch, int start, int length) throws SAXException {
        super.characters(ch, start, length);
        if(istitle && isitem){
            Log.d("NET",new String(ch,start,length));
            titles.add(new String(ch,start,length));
        }
        if (islink && isitem){
            Log.d("NET",new String(ch,start,length));
            linkSB.append(new String(ch,start,length));
        }


    }
}
