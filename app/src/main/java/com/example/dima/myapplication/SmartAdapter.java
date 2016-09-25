package com.example.dima.myapplication;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.daimajia.swipe.SwipeLayout;
import com.example.dima.myapplication.enteties.Person;
import com.example.dima.myapplication.enteties.Role;
import com.example.dima.myapplication.enteties.items.HeaderItem;
import com.example.dima.myapplication.enteties.items.ListItem;
import com.example.dima.myapplication.enteties.items.AttendingItem;
import com.example.dima.myapplication.enteties.items.NoneHeaderItem;
import com.example.dima.myapplication.enteties.items.NotAttendingItem;
import com.example.dima.myapplication.utils.ImageHelper;

import java.util.List;

import static android.R.attr.button;
import static android.R.attr.text;
import static android.graphics.BitmapFactory.decodeResource;

/**
 * Created by Dima on 22.09.2016.
 */

public class SmartAdapter extends RecyclerView.Adapter {

    private List<ListItem> items;
    private AppCompatActivity activityContext;

    public SmartAdapter(AppCompatActivity context, List<ListItem> items) {
        activityContext = context;
        this.items = items;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(activityContext);
        if (viewType == ListItem.TYPE_HEADER) {
            View itemView = layoutInflater.from(parent.getContext()).inflate(R.layout.view_list_item_header, parent, false);
            return new HeaderViewHolder(itemView);
        } else {
            ViewGroup layout;
            if (viewType == ListItem.TYPE_ATTENDING) {
                layout = (SwipeLayout) layoutInflater.from(parent.getContext()).inflate(
                        R.layout.view_list_item_attending, parent, false);
                return new AttendingViewHolder(layout);
            } else {
                layout = (RelativeLayout) layoutInflater.from(parent.getContext()).inflate(
                        R.layout.view_list_item_not_attennding, parent, false);
                return new NotAttendingViewHolder(layout);
            }
            //((ViewGroup)itemView.getParent()).removeView(itemView); // without this: java.lang.IllegalStateException: The specified child already has a parent. You must call removeView() on the child's parent first.
        }
    }

    @Override
    public void onBindViewHolder(final RecyclerView.ViewHolder viewHolder, final int position) {
        int type = getItemViewType(position);
        if (type == ListItem.TYPE_HEADER) {
            HeaderItem header = (HeaderItem) items.get(position);
            HeaderViewHolder holder = (HeaderViewHolder) viewHolder;
            holder.mTextView.setText(header.getStatus().toString());
        } else {
            NoneHeaderItem personItem;
            if (type == ListItem.TYPE_ATTENDING) {
                personItem = (AttendingItem) items.get(position);
                if (personItem.getRole().equals(Role.ADMIN)) {
                    ((AttendingViewHolder) viewHolder).swipeLayout.setLeftSwipeEnabled(false);
                }
                else {
                    ((AttendingViewHolder) viewHolder).swipeLayout.setLeftSwipeEnabled(true);
                }
            } else {
                personItem = (NotAttendingItem) items.get(position);
            }
            ViewHolder holder = (ViewHolder) viewHolder;
            TextView textView = holder.mTextView;
            ImageView imageView = holder.mImageView;
            if (personItem.getRole().equals(Role.ADMIN)) {
                textView.setTextColor(Color.parseColor("#ff00ddff"));
            }
            else {
                textView.setTextColor(Color.parseColor("#000000"));
            }
            if (personItem.getPhoto() != null) {
                Bitmap bitMap = BitmapFactory.decodeResource(activityContext.getResources(), activityContext.getResources().getIdentifier(personItem.getPhoto(), "mipmap", activityContext.getPackageName()));
                System.out.println();
                imageView.setImageBitmap(ImageHelper.getRoundedCornerBitmap(bitMap, 300));
                textView.setText(personItem.getName() + " " + personItem.getSurname());
            } else {
                textView.setText(personItem.getName().substring(0, 1).toUpperCase() + "."
                        + personItem.getSurname().substring(0, 1).toUpperCase() +
                        ".   " + personItem.getName() + " " + personItem.getSurname());
            }
        }
    }


    @Override
    public int getItemViewType(int position) {
        return items.get(position).getType();
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public void removeItem(int position) {
        items.remove(position);
        notifyItemRemoved(position);
        notifyItemRangeChanged(position, items.size());
        notifyDataSetChanged();
    }

    public ListItem getItem(int position) {
        return items.get(position);
    }

    public void addItem(ListItem listItem) {
        items.add(listItem);
        notifyItemInserted(items.size());
        notifyDataSetChanged();
    }

    public void addItem(int position, ListItem listItem) {
        items.add(position, listItem);
        notifyItemInserted(position);
        notifyItemRangeChanged(position, items.size());
        notifyDataSetChanged();
    }

    public VerticalSpaceItemDecoration verticalSpaceItemDecoration(int height) {
        return new VerticalSpaceItemDecoration(height);
    }


    public abstract class ViewHolder extends RecyclerView.ViewHolder {
        public TextView mTextView;
        public ImageView mImageView;

        public ViewHolder(View view) {
            super(view);
            mTextView = (TextView) view.findViewById(R.id.itemTextView);
        }
    }

    public class HeaderViewHolder extends ViewHolder {
        public HeaderViewHolder(View v) {
            super(v);
        }
    }

    public class AttendingViewHolder extends ViewHolder {
        public SwipeLayout swipeLayout;
        public AttendingViewHolder(View v) {
            super(v);
            mImageView = (ImageView) v.findViewById(R.id.itemImageView);
            swipeLayout = ((SwipeLayout) v);
            if (EnterActivity.myRole.equals(Role.MEMBER)) {
                swipeLayout.setSwipeEnabled(false);
            }
            else {

                swipeLayout.setShowMode(SwipeLayout.ShowMode.LayDown);
                swipeLayout.addDrag(SwipeLayout.DragEdge.Left, v.findViewById(R.id.bottom_wrapper_lswipe));
                swipeLayout.addDrag(SwipeLayout.DragEdge.Right, v.findViewById(R.id.bottom_wrapper_rswipe));
                Button buttonMakeAdm = (Button) swipeLayout.findViewById(R.id.btn_make_admin);
                buttonMakeAdm.setOnClickListener(new MakeAdminOnClickListener());
                Button buttonMakeRef = (Button) swipeLayout.findViewById(R.id.btn_make_refused);
                buttonMakeRef.setOnClickListener(new MakeRefusedOnClickListener());
            }
        }

        class MakeAdminOnClickListener implements View.OnClickListener {

            @Override
            public void onClick(View v) {
                int position = getAdapterPosition();
                NoneHeaderItem item = (NoneHeaderItem) getItem(position);
                removeItem(position);
                Person p = item.getPerson();
                p.setRole(Role.ADMIN);
                addItem(1, item);
            }
        }

        class MakeRefusedOnClickListener implements View.OnClickListener {

            @Override
            public void onClick(View v) {
                int position = getAdapterPosition();
                AttendingItem attendingItem = (AttendingItem) getItem(position);
                removeItem(position);
                Person p = attendingItem.getPerson();
                p.setRole(Role.REFUSED);
                NotAttendingItem notAttendingItem = new NotAttendingItem(p);
                addItem(notAttendingItem);
                notifyDataSetChanged();
                System.out.println("REFUSED");
            }
        }

    }

    public class NotAttendingViewHolder extends ViewHolder {
        public Button mButton;

        public NotAttendingViewHolder(View v) {
            super(v);
            mImageView = (ImageView) v.findViewById(R.id.itemImageView);
            mButton = (Button) v.findViewById(R.id.button_add);
            if (EnterActivity.myRole.equals(Role.MEMBER)) {
                ((RelativeLayout) v).removeView(mButton);
            }
            else {
                mButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        int position = getAdapterPosition();
                        NotAttendingItem notAttendingItem = (NotAttendingItem) items.get(position);
                        Person p = notAttendingItem.getPerson();
                        p.setRole(Role.MEMBER);
                        AttendingItem attendingItem = new AttendingItem(p);
                        removeItem(position);
                        addItem(1, attendingItem);

                        notifyItemRemoved(position);
                        notifyItemRangeChanged(position, items.size());
                        notifyDataSetChanged();
                    }
                });
            }
        }

    }


    public class VerticalSpaceItemDecoration extends RecyclerView.ItemDecoration {

        private final int mVerticalSpaceHeight;

        public VerticalSpaceItemDecoration(int mVerticalSpaceHeight) {
            this.mVerticalSpaceHeight = mVerticalSpaceHeight;
        }

        @Override
        public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
            int position = parent.getChildAdapterPosition(view);
            //if this is swipe view
            if (position != -1) {
                if (getItemViewType(parent.getChildAdapterPosition(view)) == ListItem.TYPE_HEADER) {
                    outRect.top = mVerticalSpaceHeight;
                }
            }
        }
    }
}
