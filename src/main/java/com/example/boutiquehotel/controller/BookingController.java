package com.example.boutiquehotel.controller;

import com.example.boutiquehotel.domain.Room;
import com.example.boutiquehotel.exception.RoomNotFoundException;
import com.example.boutiquehotel.service.BookingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

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
}
