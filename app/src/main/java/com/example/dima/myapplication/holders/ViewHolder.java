package com.example.dima.myapplication.holders;

import android.app.Activity;
import android.os.Build;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.dima.myapplication.R;
import com.example.dima.myapplication.SmartAdapter;
import com.example.dima.myapplication.enteties.items.ListItem;
import com.example.dima.myapplication.exceptions.BadViewHolderException;
import com.example.dima.myapplication.exceptions.ObjectPartlyInitializedException;

import static android.R.attr.switchMinWidth;
import static android.R.attr.type;

/**
 * Created by Dima on 27.09.2016.
 */

public abstract class ViewHolder extends RecyclerView.ViewHolder {
    protected TextView mTextView;
    protected ImageView mImageView;
    protected SmartAdapter connectedAdapter;


    ViewHolder(View view, SmartAdapter adapter) {
        super(view);
        connectedAdapter = adapter;
        mTextView = (TextView) view.findViewById(R.id.itemTextView);
    }

    abstract public void bind(ListItem item);

    //builder pattern
    public static class Builder {
        private View v;
        private SmartAdapter adapter;
        private Activity activity;

        public Builder(View v, SmartAdapter adapter) {
            this.v = v;
            this.adapter = adapter;
        }

        public Builder activity(Activity activity) {
            this.activity = activity;
            return this;
        }

        public ViewHolder build(String type) throws BadViewHolderException, ObjectPartlyInitializedException {
            switch (type) {
                case "AttendingViewHolder":
                    if (activity == null) throw new ObjectPartlyInitializedException(type + " was partly initialized. Activity is missing");
                    return new AttendingViewHolder(v, adapter, activity);
                case "NotAttendingViewHolder":
                    if (activity == null) throw new ObjectPartlyInitializedException(type + " was partly initialized. Activity is missing");
                    return new NotAttendingViewHolder(v, adapter, activity);
                case "HeaderViewHolder":
                    return new HeaderViewHolder(v, adapter);
                case "MainViewHolder":
                    return new MainViewHolder(v, adapter);
                default:
                    throw new BadViewHolderException("Incorrect type of shape :  " + type);
            }
        }
    }
}