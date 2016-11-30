package com.jirawat.jirawat_restaurant1;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

/**
 * Created by nook on 23/11/2559.
 */
public class ProductAdapter extends BaseAdapter {
    //Explicit
    private Context context;
    private String[] iconStrings, titleStrings,priceStringe;

    public ProductAdapter(Context context,
                          String[] iconStrings,
                          String[] titleStrings,
                          String[] priceStringe){
        this.context = context;
        this.iconStrings = iconStrings;
        this.titleStrings = titleStrings;
        this.priceStringe = priceStringe;
    }//Constructor
    @Override
    public int getCount() {
        return iconStrings.length;
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

        LayoutInflater layoutInflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view1 = layoutInflater.inflate(R.layout.food_listview,viewGroup,false);

        TextView titleTextView = (TextView) view1.findViewById(R.id.textView);
        titleTextView.setText(titleStrings[i]);

        TextView priceTextView = (TextView) view1.findViewById(R.id.textView2);
        priceTextView.setText(priceStringe[i]);

        ImageView iconImageView = (ImageView) view1.findViewById(R.id.imageView3);
        Picasso.with(context).load(iconStrings[i]).resize(150,150).into(iconImageView);

        return view1;
    }
}//Main class

