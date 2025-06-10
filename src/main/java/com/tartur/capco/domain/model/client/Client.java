package com.tartur.capco.domain.model.client;

import java.util.Objects;

public abstract class Client {
    private final int id;

    protected Client(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) return true;
        if (obj == null || obj.getClass() != this.getClass()) return false;
        var that = (Client) obj;
        return this.id == that.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "Client[" +
                "id=" + id + ']';
    }

}
