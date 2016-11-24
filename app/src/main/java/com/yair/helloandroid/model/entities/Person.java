package com.yair.helloandroid.model.entities;

import java.io.Serializable;

/**
 * Created by Yair on 21/11/2016.
 */

public class Person implements Serializable{
    private long id;
    private String name;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
