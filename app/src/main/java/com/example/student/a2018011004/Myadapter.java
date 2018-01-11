package com.example.student.a2018011004;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by Student on 2018/1/11.
 */

public class Myadapter extends BaseAdapter {
//    public Myadapter(Context context,){
//
//    }
    @Override
    public int getCount() {
        return 0;
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
        return null;
    }

    static class ViewHolder{
        TextView tv1;
        TextView tv2;
        ImageView img1;
    }
}
