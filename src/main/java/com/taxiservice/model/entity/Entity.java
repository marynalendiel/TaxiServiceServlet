package com.taxiservice.model.entity;

import java.io.Serializable;

/**
 * The parent class of all entities that have an identifier field.
 *
 * @author Maryna Lendiel
 */
public class Entity implements Serializable {
    private static final long serialVersionUID = -1904388428489276463L;

    private int id;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
