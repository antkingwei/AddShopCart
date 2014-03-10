package com.example.addshopcart;


import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
/**
 * 商品列表Adapter
 * @author antkingwei
 *
 */
public class GoodAdapter extends BaseAdapter{
    private Context mContext;
    private LayoutInflater layoutInflater;
    private HolderClickListener mHolderClickListener;
    final class ViewHolder {
        ImageView imgview;
        Button button;
    }
    public GoodAdapter(Context context){
        this.mContext = context;
        layoutInflater = LayoutInflater.from(mContext);
    }

    
    @Override
    public int getCount() {
        // TODO Auto-generated method stub
        return 16;
    }

    @Override
    public Object getItem(int position) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public long getItemId(int position) {
        // TODO Auto-generated method stub
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // TODO Auto-generated method stub
        final int selectedId = position;
        final ViewHolder viewHolder;
        if(convertView ==null){
            viewHolder = new ViewHolder();
            convertView = layoutInflater.inflate(R.layout.adapter_listview, null);
            viewHolder.imgview = (ImageView)convertView.findViewById(R.id.item_img);
            viewHolder.button = (Button)convertView.findViewById(R.id.item_button);
            convertView.setTag(viewHolder);
        }else{
            viewHolder =(ViewHolder)convertView.getTag();
        }
        viewHolder.button.setOnClickListener(new View.OnClickListener() {
            
            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                if(mHolderClickListener!=null){
                    int[] start_location = new int[2];
                    viewHolder.imgview.getLocationInWindow(start_location);//获取点击商品图片的位置
                    Drawable drawable = viewHolder.imgview.getDrawable();//复制一个新的商品图标
                    mHolderClickListener.onHolderClick(drawable,start_location);
                }
            }
        });
        return convertView;
    }
    public void SetOnSetHolderClickListener(HolderClickListener holderClickListener){
        this.mHolderClickListener = holderClickListener;
    }
    public interface HolderClickListener{
        public void onHolderClick(Drawable drawable,int[] start_location);
    }

}
