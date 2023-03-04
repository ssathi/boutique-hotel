package com.example.boutiquehotel.service;

import com.example.boutiquehotel.domain.RoomStatus;
import com.example.boutiquehotel.exception.NotSupportedActionException;

import java.util.Map;
import java.util.function.Function;

import static com.example.boutiquehotel.domain.RoomStatus.*;

public class RoomStateMachine {

    private static final Map<RoomStatus, Function<RoomAction, RoomStatus>> STATE_TRANSITIONS = Map.of(

            AVAILABLE, (action) -> switch (action) {
                case CHECK_IN -> OCCUPIED;
                case REPAIRED -> throwException(AVAILABLE, action);
                default -> AVAILABLE;
            },
            OCCUPIED, (action) -> switch (action) {
                case CHECK_OUT -> VACANT;
                case REPAIRED -> throwException(OCCUPIED, action);
                default -> OCCUPIED;
            },
            VACANT, (action) -> switch (action) {
                case CLEAN -> AVAILABLE;
                case OUT_OF_SERVICE -> REPAIR;
                default -> throwException(VACANT, action);
            },
            REPAIR, (action) -> switch (action) {
                case REPAIRED -> VACANT;
                default -> throwException(REPAIR, action);
            }
    );


    public static RoomStatus perform(RoomStatus status, RoomAction action) {
        return STATE_TRANSITIONS.get(status).apply(action);
    }

    private static RoomStatus throwException(RoomStatus status, RoomAction action) {
        throw new NotSupportedActionException("Action " + action + " not permitted in state: " + status);
    }
}
