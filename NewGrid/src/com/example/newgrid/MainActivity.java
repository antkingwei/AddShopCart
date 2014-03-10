
package com.example.newgrid;
import com.example.newgrid.widget.MyGridView;
import com.example.newgrid.widget.ObservableScrollView;

import android.os.Bundle;
import android.app.Activity;
import android.support.v4.app.FragmentActivity;
import android.view.Menu;
import android.view.ViewTreeObserver;
import android.widget.LinearLayout;
import android.widget.TextView;
public class MainActivity extends FragmentActivity implements ObservableScrollView.Callbacks{
    
private ObservableScrollView scrollView;

private TextView textview;

private LinearLayout linearLayout;

private MyGridView gridView;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();

    }
   private void init(){
       scrollView = (ObservableScrollView)this.findViewById(R.id.scroll_view);
       textview= (TextView)this.findViewById(R.id.top_view);
       gridView = (MyGridView)this.findViewById(R.id.grid_view);
       linearLayout = (LinearLayout)this.findViewById(R.id.linear_layout);
       scrollView.setCallbacks(this);
       scrollView.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
        
        @Override
        public void onGlobalLayout() {
            // TODO Auto-generated method stub
            onScrollChanged(scrollView.getScrollY());
        }
    });
   }
    @Override
    public void onScrollChanged(int scrollY) {
        // TODO Auto-generated method stub
        textview.setTranslationY(scrollY);
        if(scrollY==0){
            linearLayout.setTranslationY(textview.getHeight());
            
        }
    }

    @Override
    public void onDownMotionEvent() {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void onUpOrCancelMotionEvent() {
        // TODO Auto-generated method stub
        
    }

   
}
