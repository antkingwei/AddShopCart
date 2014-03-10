package com.example.newgrid.widget;

import com.example.newgrid.R;

import android.app.Activity;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class MyGridView extends ViewGroup{
    private LayoutInflater mLayoutInflater;
    private int screen_width;
    
    private int item_width;
    
    private int total = 25;
    
    private View contentView;
    private Context mContext;
    
    private int colum;
    

    public MyGridView(Context context, AttributeSet attrs) {
        super(context, attrs);
        // TODO Auto-generated constructor stub
        this.mContext = context;
        mLayoutInflater = LayoutInflater.from(context);
        WindowManager windowManager = ((Activity) context).getWindow()
                .getWindowManager();
        Display display = windowManager.getDefaultDisplay();
        screen_width = display.getWidth();
        item_width = screen_width/3;
        colum = (total%3==0) ? (total/3) : (total/3+1);
        for(int i =0;i<colum*3;i++){
            View view = getView(i,contentView,null);
            this.addView(view, item_width, item_width);
        }
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        // TODO Auto-generated method stub
        Log.e("antking", t+"");
        int childLeft =0;
        int childTop =0;
        for(int i=0;i<colum*3;i++){
            final View childView = getChildAt(i);
            childView.layout(childLeft-2, childTop-2, childLeft+item_width, childTop+item_width);
            childLeft+=item_width;
            if(childLeft+item_width>r){
                childLeft=0;
                childTop+=item_width;
            }
            
        }
        
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        // TODO Auto-generated method stub
             
        for(int i=0;i<total;i++){
            final View child = getChildAt(i);
            child.measure(MeasureSpec.UNSPECIFIED, MeasureSpec.UNSPECIFIED);
        }
        
        setMeasuredDimension(screen_width, item_width*colum);
    }
 
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
        Drawable drawable = mContext.getResources().getDrawable(R.drawable.ic_launcher);
        holderView.item_textview.setCompoundDrawablesWithIntrinsicBounds(null, drawable, null, null);
        holderView.item_textview.setText("菜单"+position);
        contentView.setOnClickListener(new View.OnClickListener() {
            
            @Override
            public void onClick(View arg0) {
                // TODO Auto-generated method stub
                Toast.makeText(mContext,"菜单", Toast.LENGTH_LONG).show();
            }
        });
        return contentView;
    }
    
    static class HolderView{
        ImageView item_imagview;
        TextView item_textview;
    }
    
    
    

}
