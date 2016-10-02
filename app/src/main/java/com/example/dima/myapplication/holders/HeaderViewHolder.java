package com.example.dima.myapplication.holders;

import android.view.View;

import com.example.dima.myapplication.SmartAdapter;
import com.example.dima.myapplication.enteties.items.HeaderItem;
import com.example.dima.myapplication.enteties.items.ListItem;

/**
 * Created by Dima on 27.09.2016.
 */

class HeaderViewHolder extends ViewHolder {
    HeaderViewHolder(View v, SmartAdapter adapter) {
        super(v, adapter);
    }

    @Override
    public void bind(ListItem item) {
        mTextView.setText(((HeaderItem) item).getStatus().toString());
    }
}
