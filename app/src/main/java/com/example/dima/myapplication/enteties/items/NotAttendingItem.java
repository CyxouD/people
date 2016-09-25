package com.example.dima.myapplication.enteties.items;

import com.example.dima.myapplication.enteties.Person;

/**
 * Created by Dima on 24.09.2016.
 */

public class NotAttendingItem extends NoneHeaderItem {
    public NotAttendingItem(Person person) {
        super(person);
    }

    @Override
    public int getType() {
        return TYPE_NOT_ATTENDING;
    }
}
