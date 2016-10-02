package com.example.dima.myapplication.holders;

import android.app.Activity;
import android.content.Context;
import android.view.View;
import android.widget.Button;

import com.daimajia.swipe.SwipeLayout;
import com.example.dima.myapplication.R;
import com.example.dima.myapplication.SmartAdapter;
import com.example.dima.myapplication.enteties.Person;
import com.example.dima.myapplication.enteties.Role;
import com.example.dima.myapplication.enteties.items.AttendingItem;
import com.example.dima.myapplication.enteties.items.ListItem;
import com.example.dima.myapplication.enteties.items.NotAttendingItem;

/**
 * Created by Dima on 27.09.2016.
 */

class AttendingViewHolder extends NoneHeaderViewHolder {
    private SwipeLayout swipeLayout;

    AttendingViewHolder(View v, SmartAdapter adapter, Activity activityContext) {
        super(v, adapter, activityContext);
        swipeLayout = ((SwipeLayout) v);
        Role userRole = Role.valueOf(activityContext.getIntent().getStringExtra("userRole"));
        if (userRole.equals(Role.MEMBER)) {
            swipeLayout.setSwipeEnabled(false);
        } else {
            swipeLayout.setShowMode(SwipeLayout.ShowMode.LayDown);
            swipeLayout.addDrag(SwipeLayout.DragEdge.Left, v.findViewById(R.id.bottom_wrapper_lswipe));
            swipeLayout.addDrag(SwipeLayout.DragEdge.Right, v.findViewById(R.id.bottom_wrapper_rswipe));
            Button buttonMakeAdm = (Button) swipeLayout.findViewById(R.id.btn_make_admin);
            buttonMakeAdm.setOnClickListener(new MakeAdminOnClickListener());
            Button buttonMakeRef = (Button) swipeLayout.findViewById(R.id.btn_make_refused);
            buttonMakeRef.setOnClickListener(new MakeRefusedOnClickListener());
        }
    }

    @Override
    public void bind(ListItem listItem) {
        super.bind(listItem);
        if (((AttendingItem) listItem).getRole().equals(Role.ADMIN)) {
            swipeLayout.setLeftSwipeEnabled(false);
        } else {
            swipeLayout.setLeftSwipeEnabled(true);
        }
    }

    class MakeAdminOnClickListener implements View.OnClickListener {

        @Override
        public void onClick(View v) {
            int position = getAdapterPosition();
            AttendingItem item = (AttendingItem) connectedAdapter.getItem(position);
            Person p = item.getPerson();
            connectedAdapter.getModificationsListener().makeAdmin(p);
            connectedAdapter.removeItem(position);
            connectedAdapter.addItem(positionAfterFirstHeader, item);
        }
    }

    class MakeRefusedOnClickListener implements View.OnClickListener {

        @Override
        public void onClick(View v) {
            int position = getAdapterPosition();
            AttendingItem attendingItem = (AttendingItem) connectedAdapter.getItem(position);
            Person p = attendingItem.getPerson();
            connectedAdapter.getModificationsListener().makeRefused(p);
            connectedAdapter.removeItem(position);
            connectedAdapter.addItem(new NotAttendingItem(p));
        }
    }

}
