package se.lexicon.data.impl;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import se.lexicon.model.ParkingSpot;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

public class ParkingSpotDaoImplTest {
    private ParkingSpotDaoImpl testObject;

    @BeforeEach
    public void setUp() {
        testObject = new ParkingSpotDaoImpl();
    }

    @Test
    public void testCreateParkingSpot() {
        ParkingSpot parkingSpot = new ParkingSpot(1, 204060);
        ParkingSpot actualValue = testObject.create(parkingSpot);
        ParkingSpot expectedValue = parkingSpot;

        assertEquals(expectedValue, actualValue);
    }

    @Test
    public void testFindParkingSpotBySpotNumber() {
        ParkingSpot parkingSpot = new ParkingSpot(1, 204060);
        testObject.create(parkingSpot);

        assertTrue(testObject.find(parkingSpot.getSpotNumber()).isPresent());
        assertEquals(1, parkingSpot.getSpotNumber());
    }
    @Test
    public void testFindNonExistingParkingSpot() {
        Optional<ParkingSpot> foundParkingSpot = testObject.find(1);

        assertFalse(foundParkingSpot.isPresent());
    }

    @Test
    public void testRemoveParkingSpot() {
        ParkingSpot parkingSpot = new ParkingSpot(1, 204060);
        testObject.create(parkingSpot);

        assertTrue(testObject.remove(parkingSpot.getSpotNumber()));
        assertFalse(testObject.find(parkingSpot.getSpotNumber()).isPresent());
    }

    @Test
    public void testRemoveNonExistingParkingSpot() {
        boolean removed = testObject.remove(1);

        assertFalse(removed);
    }

    @Test
    public void testFindAllParkingSpot() {
        ParkingSpot parkingSpot1 = new ParkingSpot(1, 204060);
        ParkingSpot parkingSpot2 = new ParkingSpot(2, 204060);
        ParkingSpot parkingSpot3 = new ParkingSpot(3, 204060);
        testObject.create(parkingSpot1);
        testObject.create(parkingSpot2);
        testObject.create(parkingSpot3);
        List<ParkingSpot> expectedValue = new ArrayList<>();
        expectedValue.add(parkingSpot1);
        expectedValue.add(parkingSpot2);
        expectedValue.add(parkingSpot3);
        List<ParkingSpot> actualValue = testObject.findAll();

        assertEquals(expectedValue, actualValue);
    }

    @Test
    public void testFindAllParkingSpotEmptyList() {
        List<ParkingSpot> expectedValue = new ArrayList<>();
        List<ParkingSpot> actualValue = testObject.findAll();

        assertEquals(expectedValue, actualValue);
    }
}