package com.example.boutiquehotel;

import com.example.boutiquehotel.domain.RoomStatus;
import com.example.boutiquehotel.exception.NotSupportedActionException;
import com.example.boutiquehotel.service.RoomAction;
import com.example.boutiquehotel.service.RoomStateMachine;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class RoomStateMachineTest {

    @Test
    public void testCheckin() {
        var nextStatus = RoomStateMachine.perform(RoomStatus.AVAILABLE, RoomAction.CHECK_IN);

        assertEquals(RoomStatus.OCCUPIED, nextStatus);
    }

    @Test
    public void testCheckOut() {
        var nextStatus = RoomStateMachine.perform(RoomStatus.OCCUPIED, RoomAction.CHECK_OUT);

        assertEquals(RoomStatus.VACANT, nextStatus);
    }

    @Test
    public void testClean() {
        var nextStatus = RoomStateMachine.perform(RoomStatus.VACANT, RoomAction.CLEAN);

        assertEquals(RoomStatus.AVAILABLE, nextStatus);
    }

    @Test
    public void testOutOfService() {
        var nextStatus = RoomStateMachine.perform(RoomStatus.VACANT, RoomAction.OUT_OF_SERVICE);

        assertEquals(RoomStatus.REPAIR, nextStatus);
    }

    @Test
    public void testRepair() {
        var nextStatus = RoomStateMachine.perform(RoomStatus.REPAIR, RoomAction.REPAIRED);

        assertEquals(RoomStatus.VACANT, nextStatus);
    }

    @Test
    public void testRepairCanGoOnlyVacant() {

        var exception = assertThrows(
                NotSupportedActionException.class,
                () -> RoomStateMachine.perform(RoomStatus.REPAIR, RoomAction.CLEAN)
        );
        assertEquals("Action CLEAN not permitted in state: REPAIR", exception.getMessage());
    }

    @Test
    public void testAvailableCantBeRepaired() {

        var exception = assertThrows(
                NotSupportedActionException.class,
                () -> RoomStateMachine.perform(RoomStatus.AVAILABLE, RoomAction.REPAIRED)
        );
        assertEquals("Action REPAIRED not permitted in state: AVAILABLE", exception.getMessage());
    }

    @Test
    public void testOccupiedCantBeRepaired() {

        var exception = assertThrows(
                NotSupportedActionException.class,
                () -> RoomStateMachine.perform(RoomStatus.OCCUPIED, RoomAction.REPAIRED)
        );
        assertEquals("Action REPAIRED not permitted in state: OCCUPIED", exception.getMessage());
    }
}
