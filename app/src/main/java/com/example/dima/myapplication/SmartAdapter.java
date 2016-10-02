package com.example.dima.myapplication;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.Rect;
import android.support.annotation.LayoutRes;
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
import com.example.dima.myapplication.enteties.Status;
import com.example.dima.myapplication.enteties.items.HeaderItem;
import com.example.dima.myapplication.enteties.items.ListItem;
import com.example.dima.myapplication.enteties.items.AttendingItem;
import com.example.dima.myapplication.enteties.items.MainHeaderItem;
import com.example.dima.myapplication.enteties.items.NoneHeaderItem;
import com.example.dima.myapplication.enteties.items.NotAttendingItem;
import com.example.dima.myapplication.exceptions.BadViewHolderException;
import com.example.dima.myapplication.exceptions.UnhandledListItem;
import com.example.dima.myapplication.exceptions.ViewHolderException;
import com.example.dima.myapplication.holders.ViewHolder;
import com.example.dima.myapplication.utils.ImageHelper;

import java.util.ArrayList;
import java.util.List;
import java.util.TreeMap;

import static android.graphics.BitmapFactory.decodeResource;

/**
 * Created by Dima on 22.09.2016.
 */

public class SmartAdapter extends RecyclerView.Adapter {

    private List<ListItem> items;
    private AppCompatActivity activityContext;
    private final int positionAfterFirstHeader = 2;
    private ModificationsListener modificationsListener;

    public SmartAdapter(AppCompatActivity context, TreeMap<Status, List<Person>> items, ModificationsListener modificationsListener) {
        activityContext = context;
        packageDataForAdapter(items);
        this.modificationsListener = modificationsListener;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view;
        try {
            switch (viewType) {
                case ListItem.TYPE_HEADER: {
                    view = getView(R.layout.view_list_item_header, parent);
                    return new ViewHolder.Builder(view, this).build("HeaderViewHolder");
                }
                case ListItem.TYPE_MAIN_HEADER: {
                    view = getView(R.layout.view_list_item_main_header, parent);
                    return new ViewHolder.Builder(view, this).build("MainViewHolder");
                }
                case ListItem.TYPE_ATTENDING: {
                    view = getView(R.layout.view_list_item_attending, parent);
                    return new ViewHolder.Builder(view, this).activity(activityContext).build("AttendingViewHolder");
                }
                case ListItem.TYPE_NOT_ATTENDING: {
                    view = getView(R.layout.view_list_item_not_attennding, parent);
                    return new ViewHolder.Builder(view, this).activity(activityContext).build("NotAttendingViewHolder");
                }
                default: {
                    try {
                        throw new UnhandledListItem("Unhandled list item recognized");
                    } catch (UnhandledListItem unhandledListItem) {
                        unhandledListItem.printStackTrace();
                        return null;
                    }
                }
            }
        } catch (ViewHolderException e) {
            e.printStackTrace();
            return null;
        }
    }

    private View getView(@LayoutRes int resource, ViewGroup parent) {
        LayoutInflater layoutInflater = LayoutInflater.from(activityContext);
        return layoutInflater.from(parent.getContext()).inflate(
                resource, parent, false);
    }

    @Override
    public void onBindViewHolder(final RecyclerView.ViewHolder viewHolder, final int position) {
        ViewHolder holder = (ViewHolder) viewHolder;
        holder.bind(items.get(position));
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
    }

    public ListItem getItem(int position) {
        return items.get(position);
    }

    public void addItem(ListItem listItem) {
        items.add(listItem);
        notifyItemInserted(items.size());
    }

    public void addItem(int position, ListItem listItem) {
        items.add(position, listItem);
        notifyItemInserted(position);
    }

    public VerticalSpaceItemDecoration verticalSpaceItemDecoration(int height) {
        return new VerticalSpaceItemDecoration(height);
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

    private void packageDataForAdapter(TreeMap<Status, List<Person>> usersMap) {
        items = new ArrayList<>();

        //Add main item header
        items.add(new MainHeaderItem(activityContext.getIntent().getStringExtra("userRole")));

        for (Status status : usersMap.keySet()) {
            HeaderItem headerItem = new HeaderItem(status);
            items.add(headerItem);
            for (Person person : usersMap.get(status)) {
                NoneHeaderItem attendingItem;
                if (status.equals(Status.ATTENDING)) {
                    attendingItem = new AttendingItem(person);
                } else {
                    attendingItem = new NotAttendingItem(person);
                }
                items.add(attendingItem);
            }
        }
    }

    public ModificationsListener getModificationsListener() {
        return modificationsListener;
    }

    public interface ModificationsListener {
        void makeAdmin(Person p);

        void makeRefused(Person p);

        void makeMember(Person p);

        void delete(Person personToDelete);

        void add(Status status, Person personToAdd);
    }
}
