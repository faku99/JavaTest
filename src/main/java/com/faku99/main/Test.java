package com.faku99.main;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "test")
public class Test {

    private int id;
    private String str;

    /**
     * Default constructor. Needed for Hibernate.
     */
    public Test() {

    }

    public Test(int id, String str) {
        this.id = id;
        this.str = str;
    }

    @Id
    public int getId() {
        return id;
    }

    public String getStr() {
        return str;
    }

    /**
     * Setter needed by Hibernate.
     *
     * @param id    Unique identifier.
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Setter needed by Hibernate.
     *
     * @param str   Useless string.
     */
    public void setStr(String str) {
        this.str = str;
    }

}
