package com.example.boutiquehotel.repo;

import com.example.boutiquehotel.domain.Room;
import com.example.boutiquehotel.domain.RoomStatus;
import com.example.boutiquehotel.exception.RoomNotFoundException;
import org.springframework.stereotype.Repository;

import java.util.*;
import java.util.stream.Collectors;

import static com.example.boutiquehotel.domain.RoomStatus.AVAILABLE;
import static java.util.Objects.isNull;

/*
* This is a in-memory database of rooms
* This implementation could be replaced to work with a persistent later.
* */

@Repository
public class InMemoryRoomRepository implements RoomRepository {


    private static final Map<String, Room> DATABASE = new LinkedHashMap<>();        // To keep the order of the rooms

    static {
        DATABASE.put("1A", new Room("1A", AVAILABLE));
        DATABASE.put("1B", new Room("1B", AVAILABLE));
        DATABASE.put("1C", new Room("1C", AVAILABLE));
        DATABASE.put("1D", new Room("1D", AVAILABLE));
        DATABASE.put("1E", new Room("1E", AVAILABLE));
        DATABASE.put("2E", new Room("2E", AVAILABLE));
        DATABASE.put("2D", new Room("2D", AVAILABLE));
        DATABASE.put("2C", new Room("2C", AVAILABLE));
        DATABASE.put("2B", new Room("2B", AVAILABLE));
        DATABASE.put("2A", new Room("2A", AVAILABLE));
        DATABASE.put("3A", new Room("3A", AVAILABLE));
        DATABASE.put("3B", new Room("3B", AVAILABLE));
        DATABASE.put("3C", new Room("3C", AVAILABLE));
        DATABASE.put("3D", new Room("3D", AVAILABLE));
        DATABASE.put("3E", new Room("3E", AVAILABLE));
        DATABASE.put("4E", new Room("4E", AVAILABLE));
        DATABASE.put("4D", new Room("4D", AVAILABLE));
        DATABASE.put("4C", new Room("4C", AVAILABLE));
        DATABASE.put("4B", new Room("4B", AVAILABLE));
        DATABASE.put("4A", new Room("4A", AVAILABLE));
    }

    /*
    * Assumption: Number of rooms in a hotel is constant.
    * It cannot be changed after construction
    * Only room status can be modified.
    * */

    private static final Map<String, Room> IMMUTABLE_DATABASE = Collections.unmodifiableMap(DATABASE);

    @Override
    public Optional<Room> findById(String id) {
        return Optional.ofNullable(IMMUTABLE_DATABASE.get(id));
    }

    @Override
    public void save(Room room) throws RoomNotFoundException {

        var exists = IMMUTABLE_DATABASE.get(room.getId());
        if (isNull(exists)) {
            throw new RoomNotFoundException("No such room exists: " + room.getId());
        }
        exists.setStatus(room.getStatus());

    }



    @Override
    public List<Room> findByStatus(RoomStatus status) {
        return IMMUTABLE_DATABASE.values()
                .stream()
                .filter(room -> room.getStatus() == status)
                .collect(Collectors.toList());
    }

    @Override
    public Optional<Room> findNearestRoomByStatus(RoomStatus status) {
        return IMMUTABLE_DATABASE.values()
                .stream()
                .filter(room -> room.getStatus() == status)
                .findFirst();
    }
}
