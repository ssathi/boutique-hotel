package com.example.boutiquehotel;

import com.example.boutiquehotel.exception.RoomNotFoundException;
import com.example.boutiquehotel.service.BookingService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static com.example.boutiquehotel.domain.RoomStatus.*;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class BoutiqueHotelApplicationTests {

	@Autowired
	private BookingService service;

	@Test
	void testCheckin() throws RoomNotFoundException {

		var room = service.checkin();

		assertEquals(OCCUPIED, room.getStatus());
	}

	@Test
	void testCheckout() throws RoomNotFoundException {

		var room = service.checkin();

		assertEquals(OCCUPIED, room.getStatus());

		var room2 = service.checkout(room.getId());

		assertEquals(VACANT, room2.getStatus());
	}

	@Test
	void testClean() throws RoomNotFoundException {

		var room = service.checkin();

		assertEquals(OCCUPIED, room.getStatus());

		var room2 = service.checkout(room.getId());

		assertEquals(VACANT, room.getStatus());

		var room3 = service.clean(room2.getId());

		assertEquals(AVAILABLE, room3.getStatus());
	}

	@Test
	void testRepair() throws RoomNotFoundException {

		var room = service.checkin();

		assertEquals(OCCUPIED, room.getStatus());

		var room2 = service.checkout(room.getId());

		assertEquals(VACANT, room2.getStatus());

		var room3 = service.outOfService(room2.getId());

		assertEquals(REPAIR, room3.getStatus());
	}

	@Test
	void testFindAllAvailable() {
		var available = service.findAllAvailable();

		assertTrue(available.stream().allMatch(room -> room.getStatus() == AVAILABLE));
	}

	@Test
	public void testInvalidRoomCheckout() {

		var exception = assertThrows(
				RoomNotFoundException.class,
				() -> service.checkout("9A")
		);
		assertEquals("Room not found: 9A", exception.getMessage());
	}

	@Test
	public void testInvalidRoomClean() {

		var exception = assertThrows(
				RoomNotFoundException.class,
				() -> service.clean("9A")
		);
		assertEquals("Room not found: 9A", exception.getMessage());
	}

	@Test
	public void testInvalidRoomOutOfService() {

		var exception = assertThrows(
				RoomNotFoundException.class,
				() -> service.outOfService("9A")
		);
		assertEquals("Room not found: 9A", exception.getMessage());
	}
}
