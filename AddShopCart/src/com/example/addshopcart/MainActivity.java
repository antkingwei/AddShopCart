package com.example.addshopcart;
import com.example.addshopcart.GoodAdapter.HolderClickListener;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.app.Activity;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.Animation.AnimationListener;
import android.view.animation.AnimationSet;
import android.view.animation.RotateAnimation;
import android.view.animation.ScaleAnimation;
import android.view.animation.TranslateAnimation;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.ListView;
/**
 * 
 * @author antkingwei
 *
 */
public class MainActivity extends Activity {
    private ListView listView;
    private Button cart_btn;
    private GoodAdapter goodAdapter;
    //动画时间
    private int AnimationDuration = 3000;
    //正在执行的动画数量
    private int number = 0;
    //是否完成清理
    private boolean isClean = false;
    private FrameLayout animation_viewGroup;
    private Handler myHandler = new Handler(){
      public void handleMessage(Message msg){
          switch(msg.what){
          case 0:
              //用来清除动画后留下的垃圾
              try{
                  animation_viewGroup.removeAllViews();
                  }catch(Exception e){
                      
                  }
                        
                  isClean = false;
                  
              break;
           default:
                  break;
          }
      }
    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        listView = (ListView)this.findViewById(R.id.listview);
        cart_btn = (Button)this.findViewById(R.id.button);
        animation_viewGroup = createAnimLayout();
        goodAdapter = new GoodAdapter(this);
        goodAdapter.SetOnSetHolderClickListener(new HolderClickListener(){

            @Override
            public void onHolderClick(Drawable drawable,int[] start_location) {
                // TODO Auto-generated method stub
                 doAnim(drawable,start_location);
                      
                
            }
            
        });
        listView.setAdapter(goodAdapter);
    }
    
    private void doAnim(Drawable drawable,int[] start_location){
        if(!isClean){
            setAnim(drawable,start_location);
        }else{
            try{
              animation_viewGroup.removeAllViews();
              isClean = false;
              setAnim(drawable,start_location);
            }catch(Exception e){
                e.printStackTrace();
            }
            finally{
                isClean = true;
            }
        }
    }
    
    /**
     * @Description: 创建动画层
     * @param
     * @return void
     * @throws
     */
    private FrameLayout createAnimLayout(){
        ViewGroup rootView = (ViewGroup)this.getWindow().getDecorView();
        FrameLayout animLayout = new FrameLayout(this);
        FrameLayout.LayoutParams lp = new FrameLayout.LayoutParams(FrameLayout.LayoutParams.MATCH_PARENT,FrameLayout.LayoutParams.MATCH_PARENT);
        animLayout.setLayoutParams(lp);
        animLayout.setBackgroundResource(android.R.color.transparent);
        rootView.addView(animLayout);
        return animLayout;
        
    }

    /**
     * @deprecated 将要执行动画的view 添加到动画层
     * @param vg
     *        动画运行的层 这里是frameLayout
     * @param view
     *        要运行动画的View
     * @param location
     *        动画的起始位置
     * @return
     */
    private View addViewToAnimLayout(ViewGroup vg,View view,int[] location){
        int x = location[0];
        int y = location[1];
        vg.addView(view);
        FrameLayout.LayoutParams lp = new FrameLayout.LayoutParams(
                dip2px(this,90),dip2px(this,90));
        lp.leftMargin = x;
        lp.topMargin = y;
        view.setPadding(5, 5, 5, 5);
        view.setLayoutParams(lp);
        
        return view;
    }
    /**
     * dip，dp转化成px 用来处理不同分辨路的屏幕
     * @param context
     * @param dpValue
     * @return
     */
    private int dip2px(Context context,float dpValue){
        float scale = context.getResources().getDisplayMetrics().density;
        return (int)(dpValue*scale +0.5f);
    }
    
   /**
    * 动画效果设置
    * @param drawable
    *       将要加入购物车的商品
    * @param start_location
    *        起始位置
    */
   private void setAnim(Drawable drawable,int[] start_location){
      
      
       Animation mScaleAnimation = new ScaleAnimation(1.5f,0.0f,1.5f,0.0f,Animation.RELATIVE_TO_SELF,0.1f,Animation.RELATIVE_TO_SELF,0.1f);
       mScaleAnimation.setDuration(AnimationDuration);
       mScaleAnimation.setFillAfter(true);
       

       final ImageView iview = new ImageView(this);
       iview.setImageDrawable(drawable);
       final View view = addViewToAnimLayout(animation_viewGroup,iview,start_location);
       view.setAlpha(0.6f);
       
       int[] end_location = new int[2];
       cart_btn.getLocationInWindow(end_location);
       int endX = end_location[0];
       int endY = end_location[1]-start_location[1];
       
       Animation mTranslateAnimation = new TranslateAnimation(0,endX,0,endY);
       Animation mRotateAnimation = new RotateAnimation(0, 180, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
       mRotateAnimation.setDuration(AnimationDuration);
       mTranslateAnimation.setDuration(AnimationDuration);
       AnimationSet mAnimationSet = new AnimationSet(true);

       mAnimationSet.setFillAfter(true);
       mAnimationSet.addAnimation(mRotateAnimation);
       mAnimationSet.addAnimation(mScaleAnimation);
       mAnimationSet.addAnimation(mTranslateAnimation);
       
       mAnimationSet.setAnimationListener(new AnimationListener(){
        
        @Override
        public void onAnimationStart(Animation animation) {
            // TODO Auto-generated method stub
            number++;
        }

        @Override
        public void onAnimationEnd(Animation animation) {
            // TODO Auto-generated method stub
          
            number--;
            if(number==0){
                isClean = true;
                myHandler.sendEmptyMessage(0);
            }
            
        }

        @Override
        public void onAnimationRepeat(Animation animation) {
            // TODO Auto-generated method stub
            
        }
           
       });
       view.startAnimation(mAnimationSet);
      
   }
   /**
    * 内存过低时及时处理动画产生的未处理冗余
    */
    @Override
   public void onLowMemory() {
    // TODO Auto-generated method stub
        isClean = true;
        try{
            animation_viewGroup.removeAllViews();
        }catch(Exception e){
            e.printStackTrace();
        }
        isClean = false;
     super.onLowMemory();
   }
    
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

}
