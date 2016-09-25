package com.example.dima.myapplication.utils;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.view.View;
import android.widget.Button;

import com.example.dima.myapplication.enteties.Person;
import com.example.dima.myapplication.enteties.Role;
import com.example.dima.myapplication.enteties.items.AttendingItem;
import com.example.dima.myapplication.enteties.items.ListItem;
import com.example.dima.myapplication.enteties.items.NoneHeaderItem;
import com.example.dima.myapplication.enteties.items.NotAttendingItem;

/**
 * Created by Dima on 25.09.2016.
 */

// May be useful later, first variant of swipe
public class MySimpleCallBack {

    /*ItemTouchHelper.SimpleCallback simpleItemTouchCallback = new ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT) {

        @Override
        public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, RecyclerView.ViewHolder target) {
            return false;
        }

        @Override
        public void onSwiped(RecyclerView.ViewHolder viewHolder, int direction) {
            int position = viewHolder.getAdapterPosition();
            if (direction == ItemTouchHelper.LEFT) {
                AttendingItem attendingItem = (AttendingItem) mAdapter.getItem(position);
                mAdapter.removeItem(position);
                Person p = attendingItem.getPerson();
                p.setRole(Role.REFUSED);
                NotAttendingItem notAttendingItem = new NotAttendingItem(p);
                mAdapter.addItem(notAttendingItem);
                mAdapter.notifyDataSetChanged();
                System.out.println("REFUSED");
            } else {
                NoneHeaderItem item = (NoneHeaderItem) mAdapter.getItem(position);
                mAdapter.removeItem(position);
                Person p = item.getPerson();
                p.setRole(Role.ADMIN);
                mAdapter.addItem(1, item);
            }
        }

        @RequiresApi(api = Build.VERSION_CODES.HONEYCOMB)
        @Override
        public void onChildDraw(Canvas c, RecyclerView recyclerView, final RecyclerView.ViewHolder viewHolder, float dX, float dY, int actionState, boolean isCurrentlyActive) {
            Paint p = new Paint();
            Bitmap icon;
            if (actionState == ItemTouchHelper.ACTION_STATE_SWIPE) {

                View itemView = viewHolder.itemView;
                float height = (float) itemView.getBottom() - (float) itemView.getTop();
                float width = height / 3;

                //make admin
                if (dX > 0) {
                    //light blue
                    p.setColor(Color.parseColor("#40E0D0"));
                    RectF background = new RectF((float) itemView.getLeft(), (float) itemView.getTop(), dX, (float) itemView.getBottom());
                    c.drawRect(background, p);
                    icon = BitmapFactory.decodeResource(getResources(), R.drawable.ic_make_admin);
                    //System.out.println(icon);
                    RectF icon_dest = new RectF((float) itemView.getLeft() + width, (float) itemView.getTop() + width, (float) itemView.getLeft() + 2 * width, (float) itemView.getBottom() - width);
                    c.drawBitmap(icon, null, icon_dest, p);
                } else { //make refused
                    //red
                    p.setColor(Color.parseColor("#FF0000"));
                    RectF background = new RectF((float) itemView.getRight() + dX, (float) itemView.getTop(), (float) itemView.getRight(), (float) itemView.getBottom());
                    c.drawRect(background, p);
                    icon = BitmapFactory.decodeResource(getResources(), R.drawable.ic_make_refused);
                    //System.out.println(icon);
                    Button button = new Button(MainActivity.this);
                    button.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            makeRefused(viewHolder.getAdapterPosition());
                        }
                    });
                    RectF icon_dest = new RectF((float) itemView.getRight() - 2 * width, (float) itemView.getTop() + width, (float) itemView.getRight() - width, (float) itemView.getBottom() - width);
                    c.drawBitmap(icon, null, icon_dest, p);

                }
            }
            super.onChildDraw(c, recyclerView, viewHolder, dX, dY, actionState, isCurrentlyActive);
        }

        private void makeAdmin(int position) {
            NoneHeaderItem item = (NoneHeaderItem) mAdapter.getItem(position);
            mAdapter.removeItem(position);
            Person p = item.getPerson();
            p.setRole(Role.ADMIN);
            mAdapter.addItem(1, item);
        }

        private void makeRefused(int position) {
            AttendingItem attendingItem = (AttendingItem) mAdapter.getItem(position);
            mAdapter.removeItem(position);
            Person p = attendingItem.getPerson();
            p.setRole(Role.REFUSED);
            NotAttendingItem notAttendingItem = new NotAttendingItem(p);
            mAdapter.addItem(notAttendingItem);
            mAdapter.notifyDataSetChanged();
            System.out.println("REFUSED");
        }

        @Override
        public int getMovementFlags(RecyclerView recyclerView,
                                    RecyclerView.ViewHolder viewHolder) {

            int swipeFlags = 0;
            if (!(viewHolder instanceof SmartAdapter.HeaderViewHolder)) {
                ListItem listItem = mAdapter.getItem(viewHolder.getAdapterPosition());
                Role role = ((NoneHeaderItem) listItem).getRole();
                System.out.println(role);
                switch (role) {
                    case MEMBER: {
                        swipeFlags = ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT; // both directions are enabled
                        break;
                    }
                    case ADMIN: {
                        swipeFlags = ItemTouchHelper.LEFT;  // only direction with make_refuse action is enabled
                        break;
                    }
                }
            }
            return makeMovementFlags(0, swipeFlags);
        }

    };
    ItemTouchHelper itemTouchHelper = new ItemTouchHelper(simpleItemTouchCallback);
    itemTouchHelper.attachToRecyclerView(mRecyclerView);
}*/

}
