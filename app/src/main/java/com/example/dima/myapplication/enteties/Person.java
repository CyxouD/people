package com.example.dima.myapplication.enteties;

/**
 * Created by Dima on 23.09.2016.
 */

public class Person implements Cloneable {
    private Role role;
    private String name;
    private String surname;
    private String photo;

    public Person(Role role, String name, String surname, String photo) {
        this.role = role;
        this.name = name;
        this.surname = surname;
        this.photo = photo;
    }


    public Role getRole() {
        return role;
    }

    public String getName() {
        return name;
    }

    public String getSurname() {
        return surname;
    }

    public String getPhoto() {
        return photo;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    @Override
    public Person clone() throws CloneNotSupportedException {
        return (Person) super.clone();
    }

    @Override
    public String toString() {
        return "Person{" +
                "role=" + role +
                ", name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", photo='" + photo + '\'' +
                '}';
    }
}
