package com.example.dima.myapplication.enteties.items;

import com.example.dima.myapplication.enteties.Person;
import com.example.dima.myapplication.enteties.Role;

/**
 * Created by Dima on 23.09.2016.
 */

public class AttendingItem extends NoneHeaderItem {

    public AttendingItem(Person person) {
        super(person);
    }

    @Override
    public int getType() {
        return TYPE_ATTENDING;
    }
}
