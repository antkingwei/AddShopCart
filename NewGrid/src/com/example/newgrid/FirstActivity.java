package com.example.newgrid;

import com.example.newgrid.widget.MyGridView;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.widget.ScrollView;
import android.widget.TextView;

public class FirstActivity extends FragmentActivity{
    
    private ScrollView scrollView;
    
    private MyGridView gridView;
    
    private TextView textView;
    
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_first);
        init();

    }
   private void init(){
       
   }

}
