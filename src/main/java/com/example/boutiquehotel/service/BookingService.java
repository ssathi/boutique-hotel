package com.example.boutiquehotel.service;

import com.example.boutiquehotel.domain.Room;
import com.example.boutiquehotel.exception.RoomNotFoundException;
import com.example.boutiquehotel.repo.RoomRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.example.boutiquehotel.domain.RoomStatus.AVAILABLE;
import static com.example.boutiquehotel.service.RoomAction.*;

@Service
public class BookingService {

    @Autowired
    private RoomRepository roomRepo;

    public Room checkin() throws RoomNotFoundException {

        var available = roomRepo.findNearestRoomByStatus(AVAILABLE);

        if (available.isEmpty()) {
            throw new RoomNotFoundException("No room available");
        }

        var room = available.get();

        var nextStatus = RoomStateMachine.perform(room.getStatus(), CHECK_IN);

        room.setStatus(nextStatus);
        roomRepo.save(room);

        return room;
    }

    public Room checkout(String id) throws RoomNotFoundException {

        var found = roomRepo.findById(id);

        if (found.isEmpty()) {
            throw new RoomNotFoundException("Room not found: " + id);
        }

        var room = found.get();

        var nextStatus = RoomStateMachine.perform(room.getStatus(), CHECK_OUT);

        room.setStatus(nextStatus);
        roomRepo.save(room);

        return room;
    }

    public Room clean(String id) throws RoomNotFoundException {

        var found = roomRepo.findById(id);

        if (found.isEmpty()) {
            throw new RoomNotFoundException("Room not found: " + id);
        }

        var room = found.get();

        var nextStatus = RoomStateMachine.perform(room.getStatus(), CLEAN);

        room.setStatus(nextStatus);
        roomRepo.save(room);

        return room;
    }

    public Room outOfService(String id) throws RoomNotFoundException {

        var found = roomRepo.findById(id);

        if (found.isEmpty()) {
            throw new RoomNotFoundException("Room not found: " + id);
        }

        var room = found.get();

        var nextStatus = RoomStateMachine.perform(room.getStatus(), OUT_OF_SERVICE);

        room.setStatus(nextStatus);
        roomRepo.save(room);

        return room;
    }


    public List<Room> findAllAvailable() {
        return roomRepo.findByStatus(AVAILABLE);
    }
}
