package com.example.dima.myapplication.holders;

import android.view.View;
import android.widget.ImageView;

import com.example.dima.myapplication.R;
import com.example.dima.myapplication.SmartAdapter;
import com.example.dima.myapplication.enteties.items.ListItem;
import com.example.dima.myapplication.enteties.items.MainHeaderItem;

/**
 * Created by Dima on 27.09.2016.
 */

class MainViewHolder extends ViewHolder {

    MainViewHolder(View view, SmartAdapter adapter) {
        super(view, adapter);
        mImageView = (ImageView) view.findViewById(R.id.myPhotoImgView);
    }

    @Override
    public void bind(ListItem item) {
        mTextView.setText(((MainHeaderItem) item).getRole().toString());
        mImageView.setBackgroundResource(R.mipmap.me);
    }
}