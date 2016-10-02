package com.example.dima.myapplication.enteties.items;

/**
 * Created by Dima on 23.09.2016.
 */

public abstract class ListItem {
    public static final int TYPE_HEADER = 0;
    public static final int TYPE_ATTENDING = 1;
    public static final int TYPE_NOT_ATTENDING = 2;
    public static final int TYPE_MAIN_HEADER = 3;
    abstract public int getType();


}
