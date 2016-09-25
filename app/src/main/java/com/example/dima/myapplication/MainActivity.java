package com.example.dima.myapplication;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.support.annotation.DrawableRes;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.dima.myapplication.enteties.Person;
import com.example.dima.myapplication.enteties.Role;
import com.example.dima.myapplication.enteties.Status;
import com.example.dima.myapplication.enteties.items.HeaderItem;
import com.example.dima.myapplication.enteties.items.ListItem;
import com.example.dima.myapplication.enteties.items.AttendingItem;
import com.example.dima.myapplication.utils.TestData;
import com.example.dima.myapplication.enteties.items.NoneHeaderItem;
import com.example.dima.myapplication.enteties.items.NotAttendingItem;

import java.util.ArrayList;
import java.util.List;
import java.util.TreeMap;

import static com.example.dima.myapplication.EnterActivity.myRole;

public class MainActivity extends AppCompatActivity {
    private RecyclerView mRecyclerView;
    private SmartAdapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    private final int VERTICAL_ITEM_SPACE = 24;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar myToolbar = (Toolbar) findViewById(R.id.my_toolbar);
        myToolbar.setTitle("People");
        setSupportActionBar(myToolbar);

        TextView textView = (TextView) findViewById(R.id.statusView);
        textView.setText(myRole.toString());
        ImageView imageView = (ImageView) findViewById(R.id.myPhotoImgView);
        imageView.setBackgroundResource(R.mipmap.me);

        mRecyclerView = (RecyclerView) findViewById(R.id.my_recycler_view);

        // use a linear layout manager
        mLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLayoutManager);

        TreeMap<Status, List<Person>> usersMap = TestData.createTestData();
        // specify an adapter (see also next example)
        mAdapter = new SmartAdapter(this, packageDataForAdapter(usersMap));
        mRecyclerView.addItemDecoration(((SmartAdapter) mAdapter).verticalSpaceItemDecoration(VERTICAL_ITEM_SPACE));
        mRecyclerView.setAdapter(mAdapter);
    }

    public Drawable getDrawableFromId(@DrawableRes int id) {
        Drawable drawable;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            drawable = getResources().getDrawable(id, getTheme());
        } else {
            drawable = getResources().getDrawable(id);
        }

        return drawable;
    }

    private List<ListItem> packageDataForAdapter(TreeMap<Status, List<Person>> usersMap) {
        List<ListItem> adapterItems = new ArrayList<>();

        for (Status status : usersMap.keySet()) {
            HeaderItem headerItem = new HeaderItem(status);
            adapterItems.add(headerItem);
            for (Person person : usersMap.get(status)) {
                NoneHeaderItem attendingItem;
                if (status.equals(Status.ATTENDING)) {
                    attendingItem = new AttendingItem(person);
                } else {
                    attendingItem = new NotAttendingItem(person);
                }
                adapterItems.add(attendingItem);
            }
        }

        return adapterItems;
    }



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_close:
                Intent enterActivity = new Intent(MainActivity.this, EnterActivity.class);
                myRole = Role.MEMBER;
                startActivity(enterActivity);
                return true;
            default:
                // If we got here, the user's action was not recognized.
                // Invoke the superclass to handle it.
                return super.onOptionsItemSelected(item);
        }
    }



  /*  public static class MainActivityFragment extends Fragment {
        @Nullable
        @Override
        public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
            return super.onCreateView(inflater, container, savedInstanceState);
        }
    }*/
}
