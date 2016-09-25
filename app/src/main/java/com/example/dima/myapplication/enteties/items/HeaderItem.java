package com.example.dima.myapplication.enteties.items;

import com.example.dima.myapplication.enteties.Role;
import com.example.dima.myapplication.enteties.Status;

/**
 * Created by Dima on 23.09.2016.
 */

public class HeaderItem extends ListItem {
    private Status status;

    public HeaderItem(Status status) {
        this.status = status;
    }

    public Status getStatus() {
        return status;
    }

    @Override
    public int getType() {
        return TYPE_HEADER;
    }

    @Override
    public String toString() {
        return "HeaderItem{" +
                "status=" + status +
                '}';
    }
}
