package com.example.dima.myapplication;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import com.example.dima.myapplication.enteties.Person;
import com.example.dima.myapplication.enteties.Role;
import com.example.dima.myapplication.enteties.Status;
import com.example.dima.myapplication.utils.TestData;

import java.util.List;
import java.util.TreeMap;


public class MainActivity extends AppCompatActivity {
    private RecyclerView mRecyclerView;
    private SmartAdapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    private final int VERTICAL_ITEM_SPACE = 24;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        configureToolbar();
        configureRecyclingView();
    }

    private void configureToolbar() {
        Toolbar myToolbar = (Toolbar) findViewById(R.id.my_toolbar);
        myToolbar.setTitle("People");
        setSupportActionBar(myToolbar);
    }

    private void configureRecyclingView() {
        mRecyclerView = (RecyclerView) findViewById(R.id.my_recycler_view);

        // use a linear layout manager
        mLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLayoutManager);

        final TreeMap<Status, List<Person>> usersMap = TestData.createTestData();
        // specify an adapter (see also next example)
        mAdapter = new SmartAdapter(this, usersMap, new SmartAdapter.ModificationsListener() {
            @Override
            public void makeAdmin(Person p) {
                delete(p);
                p.setRole(Role.ADMIN);
                add(Status.ATTENDING, p);
                System.out.println(usersMap);
            }

            @Override
            public void makeRefused(Person p) {
                delete(p);
                p.setRole(Role.REFUSED);
                add(Status.NOT_ATTENDING, p);
                System.out.println(usersMap);
            }

            @Override
            public void makeMember(Person p) {
                delete(p);
                p.setRole(Role.MEMBER);
                add(Status.ATTENDING, p);
                System.out.println(usersMap);
            }

            @Override
            public void delete(Person personToDelete) {
                for (List<Person> personList : usersMap.values()) {
                    for (Person p : personList) {
                        if (p.equals(personToDelete)) {
                            personList.remove(personToDelete);
                            break; // to avoid java.util.ConcurrentModificationException
                        }
                    }
                }
            }

            @Override
            public void add(Status status, Person personToAdd) {
                usersMap.get(status).add(personToAdd);
            }


        });
        mRecyclerView.addItemDecoration(mAdapter.verticalSpaceItemDecoration(VERTICAL_ITEM_SPACE));
        mRecyclerView.setAdapter(mAdapter);
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
                finish();
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
