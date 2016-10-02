package com.example.dima.myapplication.holders;

import android.app.Activity;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;

import com.example.dima.myapplication.R;
import com.example.dima.myapplication.SmartAdapter;
import com.example.dima.myapplication.enteties.Person;
import com.example.dima.myapplication.enteties.Role;
import com.example.dima.myapplication.enteties.items.AttendingItem;
import com.example.dima.myapplication.enteties.items.NotAttendingItem;

/**
 * Created by Dima on 27.09.2016.
 */

class NotAttendingViewHolder extends NoneHeaderViewHolder {
    private Button mButton;

    NotAttendingViewHolder(View v, SmartAdapter adapter, Activity activityContext) {
        super(v, adapter, activityContext);
        mButton = (Button) v.findViewById(R.id.button_add);
        Role userRole = Role.valueOf(activityContext.getIntent().getStringExtra("userRole"));
        if (userRole.equals(Role.MEMBER)) {
            ((RelativeLayout) v).removeView(mButton);
        } else {
            mButton.setOnClickListener(new MakeMemberOnClickListener());
        }
    }

    class MakeMemberOnClickListener implements View.OnClickListener {

        @Override
        public void onClick(View v) {
            int position = getAdapterPosition();
            NotAttendingItem notAttendingItem = (NotAttendingItem) connectedAdapter.getItem(position);
            Person p = notAttendingItem.getPerson();
            connectedAdapter.getModificationsListener().makeMember(p);
            connectedAdapter.removeItem(position);
            connectedAdapter.addItem(positionAfterFirstHeader, new AttendingItem(p));
        }
    }

}
