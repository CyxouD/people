package com.example.dima.myapplication.utils;

import com.example.dima.myapplication.enteties.Person;
import com.example.dima.myapplication.enteties.Role;
import com.example.dima.myapplication.enteties.Status;

import java.util.ArrayList;
import java.util.List;
import java.util.TreeMap;

/**
 * Created by Dima on 23.09.2016.
 */

public class TestData {

    public static TreeMap<Status, List<Person>> createTestData() {
        TreeMap<Status, List<Person>> treeMap = new TreeMap<>();

        treeMap.put(Status.ATTENDING, new ArrayList<Person>()
        {
            {
                add(new Person(Role.ADMIN, "Dima", "Zasuha", "photo1"));
                add(new Person(Role.MEMBER, "Vasya", "Pupkin", "photo2"));
                add(new Person(Role.MEMBER, "Dasha", "Traveller", "photo3"));
            }
        });
        treeMap.put(Status.NOT_ATTENDING, new ArrayList<Person>()
        {
            {
                add(new Person(Role.REFUSED, "Refused", "Man", null));
            }
        });

        return treeMap;
    }
}
