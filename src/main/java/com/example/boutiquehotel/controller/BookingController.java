package com.example.boutiquehotel.controller;

import com.example.boutiquehotel.domain.Room;
import com.example.boutiquehotel.exception.RoomNotFoundException;
import com.example.boutiquehotel.service.BookingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class BookingController {

    @Autowired
    private BookingService service;

    @PostMapping("/hotel/room/checkin")
    public ResponseEntity<Room> checkin() throws RoomNotFoundException {
        return ResponseEntity.ok(service.checkin());
    }

    @PostMapping("/hotel/room/{id}/checkout")
    public ResponseEntity<Room> checkout(@PathVariable String id) throws RoomNotFoundException {
        return ResponseEntity.ok(service.checkout(id));
    }

    @PostMapping("/hotel/room/{id}/clean")
    public ResponseEntity<Room> clean(@PathVariable String id) throws RoomNotFoundException {
        return ResponseEntity.ok(service.clean(id));
    }

    @PostMapping("/hotel/room/{id}/outofservice")
    public ResponseEntity<Room> outOfService(@PathVariable String id) throws RoomNotFoundException {
        return ResponseEntity.ok(service.outOfService(id));
    }

    @GetMapping ("/hotel/room")
    public ResponseEntity<List<Room>> getAvailableRooms() {
        return ResponseEntity.ok(service.findAllAvailable());
    }
}
