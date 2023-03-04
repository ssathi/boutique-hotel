package com.example.boutiquehotel.repo;

import com.example.boutiquehotel.domain.Room;
import com.example.boutiquehotel.domain.RoomStatus;
import com.example.boutiquehotel.exception.RoomNotFoundException;

import java.util.List;
import java.util.Optional;

public interface RoomRepository {

    Optional<Room> findById(String id);

    void save(Room room) throws RoomNotFoundException;

    List<Room> findByStatus(RoomStatus status);

    Optional<Room> findNearestRoomByStatus(RoomStatus status);
}
