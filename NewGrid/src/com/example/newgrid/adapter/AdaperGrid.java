package com.example.newgrid.adapter;

import com.example.newgrid.R;
import com.example.newgrid.model.ItemBean;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;


import java.util.List;

public class AdaperGrid extends BaseAdapter{
    private List<ItemBean> list;
    
    private Context mContext;
    
    private LayoutInflater mLayoutInflater;
    
    static class HolderView{
        ImageView item_imagview;
        TextView item_textview;
    }
    
    public AdaperGrid(Context context,List<ItemBean> list){
        this.mContext = context;
        this.list = list;
        mLayoutInflater = LayoutInflater.from(mContext);
    }

    @Override
    public int getCount() {
        // TODO Auto-generated method stub
        return 11;
    }

    @Override
    public Object getItem(int arg0) {
        // TODO Auto-generated method stub
        return list.get(arg0);
    }

    @Override
    public long getItemId(int arg0) {
        // TODO Auto-generated method stub
        return arg0;
    }

    @Override
    public View getView(int position, View contentView, ViewGroup viewParent) {
        // TODO Auto-generated method stub
        HolderView holderView = null;
        if(contentView==null){
            contentView = mLayoutInflater.inflate(R.layout.adapter_item, null);
            holderView = new HolderView();
//            holderView.item_imagview = (ImageView)contentView.findViewById(R.id.item_imagview);
            holderView.item_textview = (TextView)contentView.findViewById(R.id.item_textview);
            contentView.setTag(holderView);
        }else{
            holderView = (HolderView)contentView.getTag();
            
        }
        
        return contentView;
    }

}
