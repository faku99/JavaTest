package com.faku99.main;

import javax.persistence.*;

@Entity
@Table(name = "person")
@Access(AccessType.FIELD)
public class Person {

    //! Unique identifier.
    @Id
    @GeneratedValue
    private int id;

    //! Person's name.
    private String name;

    //! Person's age.
    private int age;

    public Person(String name, int age) {
        this.name = name;
        this.age = age;
    }

// region Public methods

    /*public int getAge() {
        return age;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }*/

    @Override
    public String toString() {
        return String.format("(%d) %s - %d yo", id, name, age);
    }

// endregion

// region Private methods

    /*private void setAge(int age) {
        this.age = age;
    }

    private void setId(int id) {
        this.id = id;
    }

    private void setName(String name) {
        this.name = name;
    }*/

// endregion
}
