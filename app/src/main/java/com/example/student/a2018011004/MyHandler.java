package com.example.student.a2018011004;

import android.content.Context;
import android.util.Log;
import android.widget.BaseAdapter;

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
    boolean isdescription=false;
    StringBuilder linkSB=new StringBuilder();//弄一個大字串物件給連結存放用,不能像title直接抓，因為會抓到沒有item的連結，title也會，但用isitem處理，兩個方法疑似都行
   // StringBuilder descriptionSB=new StringBuilder();刪除

public ArrayList<mobile01newitem> newitems=new ArrayList<>();
mobile01newitem item;
//public ArrayList<String> links=new ArrayList<>();//弄一個陣列物件給連結存放用 刪除
//public ArrayList<String> descriptions=new ArrayList<>();//弄一個陣列物件給連結存放用刪除

    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
        super.startElement(uri, localName, qName, attributes);

        //用switch取代if
        switch (qName){
            case "title":
                istitle=true;
                break;
            case "item":
                isitem=true;
                item=new mobile01newitem();//新增物件
                break;
            case "link":
                islink=true;
                break;
            case "description":
                isdescription=true;
                break;
        }

//   刪除     if(qName.equals("title")){            istitle=true;        }
//     刪除   if(qName.equals("item")){            isitem=true;            item= new mobile01newitem();//item的頭就新增物件        }
//     刪除   if (qName.equals("link")){            islink=true;        }
    }

    @Override
    public void endElement(String uri, String localName, String qName) throws SAXException {
        super.endElement(uri, localName, qName);
        switch (qName){
            case "title":
                istitle=false;
                break;
            case "item":
                isitem=false;
                newitems.add(item);//把剛剛新增的物件丟進去
                break;
            case "link":
                islink=false;
                if(isitem){
                    item.link=linkSB.toString();
                    linkSB=new StringBuilder();
                }
                break;
            case "description":
                isdescription=false;
                break;
        }
//    刪除    if(qName.equals("title")){           istitle=false;       }
//        if(qName.equals("item")){            isitem=false;            newitems.add(item);//把剛剛新增的物件丟進去        }
//        if(qName.equals("link")){            islink=false;
//            if(isitem){//在item等於true(原始碼有item標籤)的時候才執行放入link的動作
//                item.link=linkSB.toString();//把mobile01的物件塞入item
////            links.add(linkSB.toString());
////            linkSB=new StringBuilder();//清除linkSB的內容
//            }
//        }
//        if (qName.equals("description")){
//            isdescription=false;
//            if(isitem){//在item等於true(原始碼有item標籤)的時候才執行放入description的動作
//                descriptions.add(descriptionSB.toString());
//                descriptionSB=new StringBuilder();//清除linkSB的內容
//            }
//        }刪除
    }

    @Override
    public void characters(char[] ch, int start, int length) throws SAXException {
        super.characters(ch, start, length);
        if(istitle && isitem){
            Log.d("NET",new String(ch,start,length));
            item.title=new String(ch,start,length);
//            titles.add(new String(ch,start,length)); 刪除
        }
        if (islink && isitem){
            Log.d("NET",new String(ch,start,length));
            linkSB.append(new String(ch,start,length));
        }
        if (isdescription && isitem){
            Log.d("NET",new String(ch,start,length));
            item.description=new String(ch,start,length);
        }

    }



}
