package com.dotplays.messaggio.model;

import java.io.Serializable;

public class Group  implements Serializable {

    public String name;
    public String description;
    public String id;
    public String lastUpdate;
    public long time;

    public long getTime() {
        return time;
    }

    public void setTime(long time) {
        this.time = time;
    }

    public Group() {


    }

    public Group(String name, String description, String id, String lastUpdate) {
        this.name = name;
        this.description = description;
        this.id = id;
        this.lastUpdate = lastUpdate;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getLastUpdate() {
        return lastUpdate;
    }

    public void setLastUpdate(String lastUpdate) {
        this.lastUpdate = lastUpdate;
    }

}
