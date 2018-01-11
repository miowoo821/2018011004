package com.example.student.a2018011004;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

/**
 * Created by Student on 2018/1/11.
 */

public class Myadapter extends BaseAdapter {
    Context context;
    ArrayList<mobile01newitem> mylist;
    public Myadapter(Context context, ArrayList<mobile01newitem>mylist){
        this.context=context;
        this.mylist=mylist;
    }
    @Override
    //設定要回傳資料的筆數(總共會有幾格)
    public int getCount() {//

        return mylist.size();
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        ViewHolder viewholder;
        if(view==null){
            LayoutInflater inflater=LayoutInflater.from(context);
            view=inflater.inflate(R.layout.item,null);//把自己做的LAYOUT丟進去剛剛新增的view中
            viewholder=new ViewHolder();
            viewholder.tv1=view.findViewById(R.id.textView4);
            viewholder.tv2=view.findViewById(R.id.textView3);
            viewholder.img1=view.findViewById(R.id.imageView);
            view.setTag(viewholder);

        }
        else {
            viewholder=(ViewHolder)view.getTag();
        }
        viewholder.tv2.setText(mylist.get(i).title);
        viewholder.tv1.setText(mylist.get(i).description);
        Picasso.with(context).load(mylist.get(i).imgurl).into(viewholder.img1);
        Log.d("NET", "title:" + mylist.get(i).title + ", img:" + mylist.get(i).imgurl);


        return view;
    }

    static class ViewHolder{
        TextView tv1;
        TextView tv2;
        ImageView img1;
    }
}
