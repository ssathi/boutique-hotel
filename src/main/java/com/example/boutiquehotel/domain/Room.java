package com.example.boutiquehotel.domain;

public class Room {
    private String id;
    private RoomStatus status;

    public Room() {
    }

    public Room(String id, RoomStatus status) {
        this.id = id;
        this.status = status;
    }

    public String getId() {
        return id;
    }

    public RoomStatus getStatus() {
        return status;
    }

    public void setStatus(RoomStatus status) {
        this.status = status;
    }
}
