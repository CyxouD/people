package com.example.dima.myapplication.enteties.items;

import com.example.dima.myapplication.enteties.Person;
import com.example.dima.myapplication.enteties.Role;

/**
 * Created by Dima on 24.09.2016.
 */

public abstract class NoneHeaderItem extends ListItem {
    private Person person;

    public NoneHeaderItem(Person person) {
        this.person = person;
    }

    public String getName() {
        return person.getName();
    }

    public String getSurname() {
        return person.getSurname();
    }

    public String getPhoto() {
        return person.getPhoto();
    }

    public void setRole(Role role) {
        person.setRole(role);
    }

    public Role getRole() {
        return person.getRole();
    }

    public Person getPerson() {
        return person;
    }

    @Override
    public String toString() {
        return "NoneHeaderItem{" +
                "person=" + person +
                '}';
    }
}
