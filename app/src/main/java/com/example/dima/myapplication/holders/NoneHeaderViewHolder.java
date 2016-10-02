package com.example.dima.myapplication.holders;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.view.View;
import android.widget.ImageView;

import com.example.dima.myapplication.R;
import com.example.dima.myapplication.SmartAdapter;
import com.example.dima.myapplication.enteties.Role;
import com.example.dima.myapplication.enteties.items.ListItem;
import com.example.dima.myapplication.enteties.items.NoneHeaderItem;
import com.example.dima.myapplication.utils.ImageHelper;

/**
 * Created by Dima on 02.10.2016.
 */

abstract class NoneHeaderViewHolder extends ViewHolder {
    private Activity activityContext;
    protected final int positionAfterFirstHeader = 2;


    NoneHeaderViewHolder(View view, SmartAdapter adapter, Activity activityContext) {
        super(view, adapter);
        mImageView = (ImageView) view.findViewById(R.id.itemImageView);
        this.activityContext = activityContext;
    }

    @Override
    public void bind(ListItem listItem) {
        NoneHeaderItem personItem = (NoneHeaderItem) listItem;
        if (personItem.getRole().equals(Role.ADMIN)) {
            mTextView.setTextColor(Color.parseColor("#ff00ddff"));
        } else {
            mTextView.setTextColor(Color.parseColor("#000000"));
        }
        if (personItem.getPhoto() != null) {
            Bitmap bitMap = BitmapFactory.decodeResource(activityContext.getResources(), activityContext.getResources().getIdentifier(personItem.getPhoto(), "mipmap", activityContext.getPackageName()));
            mImageView.setImageBitmap(ImageHelper.getRoundedCornerBitmap(bitMap, 300));
            mTextView.setText(personItem.getName() + " " + personItem.getSurname());
        } else {
            mTextView.setText(personItem.getName().substring(0, 1).toUpperCase() + "."
                    + personItem.getSurname().substring(0, 1).toUpperCase() +
                    ".   " + personItem.getName() + " " + personItem.getSurname());
        }
    }
}