package ua.epam.internetprovider.db.entity;

import java.io.Serializable;

public abstract class Entity implements Serializable {
    private long id;

    public Entity() {
    }

    public Entity(long id) {
        this.id = id;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }
}
