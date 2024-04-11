package se.lexicon.data.impl;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import se.lexicon.model.*;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

public class ReservationDaoImplTest {
    private ReservationDaoImpl testObject;
    private Customer customer;
    private ParkingSpot parkingSpot;
    private Vehicle vehicle;

    @BeforeEach
    public void setUp() {
        testObject = new ReservationDaoImpl();
        customer = new Customer(1, "John Doe", "123456789");
        parkingSpot = new ParkingSpot(1, 204060);
        vehicle = new Vehicle("DFG456", VehicleType.CAR);
    }

    @Test
    public void testCreateReservation() {
        Reservation reservation = new Reservation(customer, parkingSpot, 2, vehicle);
        Reservation actualValue = testObject.create(reservation);
        Reservation expectedValue = reservation;

        assertEquals(expectedValue, actualValue);
        assertTrue(testObject.find(reservation.getId()).isPresent());
    }

    @Test
    public void testFindReservationById() {
        Reservation reservation = new Reservation(customer, parkingSpot, 2, vehicle);
        testObject.create(reservation);

        assertTrue(testObject.find(reservation.getId()).isPresent());
    }

    @Test
    public void testFindNonExistingReservationById() {
        Optional<Reservation> foundReservation = testObject.find("ABC123");

        assertFalse(foundReservation.isPresent());
    }

    @Test
    public void testRemoveReservation() {
        Reservation reservation = new Reservation(customer, parkingSpot, 2, vehicle);
        testObject.create(reservation);

        assertTrue(testObject.remove(reservation.getId()));
        assertFalse(testObject.find(reservation.getId()).isPresent());
    }

    @Test
    public void testRemoveNonExistingReservation() {
        boolean removed = testObject.remove("ABC123");

        assertFalse(removed);
    }

    @Test
    public void testFindReservationByCustomerId() {
        Reservation reservation = new Reservation(customer, parkingSpot, 2, vehicle);
        testObject.create(reservation);
        int expectedValue = 1;
        Reservation foundReservation = testObject.findByCustomerId(expectedValue);

        assertNotNull(foundReservation);
        assertEquals(expectedValue, foundReservation.getCustomer().getId());
    }

    @Test
    public void testFindReservationByParkingSpotNumber() {
        Reservation reservation = new Reservation(customer, parkingSpot, 2, vehicle);
        testObject.create(reservation);
        int expectedValue = 1;
        Reservation foundReservation = testObject.findByParkingSpotNumber(expectedValue);

        assertNotNull(foundReservation);
        assertEquals(expectedValue, foundReservation.getParkingSpot().getSpotNumber());
    }

    @Test
    public void testFindReservationByVehicleLicensePlate() {
        Reservation reservation = new Reservation(customer, parkingSpot, 2, vehicle);
        testObject.create(reservation);
        String expectedValue = "DFG456";
        Reservation foundReservation = testObject.findByVehicleLicensePlate(expectedValue);

        assertNotNull(foundReservation);
        assertEquals(expectedValue, foundReservation.getAssociatedVehicle().getLicensePlate());
    }
}