package com.example.dima.myapplication.enteties.items;

import android.preference.PreferenceActivity;

import com.example.dima.myapplication.enteties.Role;
import com.example.dima.myapplication.enteties.Status;

/**
 * Created by Dima on 27.09.2016.
 */

public class MainHeaderItem extends ListItem {
    public Role role;

    public MainHeaderItem(String roleValue) {
        role = Role.valueOf(roleValue);
    }

    public Role getRole() {
        return role;
    }

    @Override
    public int getType() {
        return ListItem.TYPE_MAIN_HEADER;
    }
}
