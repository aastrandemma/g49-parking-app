package se.lexicon.data.impl;

import se.lexicon.data.ReservationDao;
import se.lexicon.model.Reservation;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public class ReservationDaoImpl implements ReservationDao {
    private List<Reservation> storage = new ArrayList<>();
    @Override
    public Reservation create(Reservation reservation) {
        if (reservation == null) throw new IllegalArgumentException("Reservation can't be null");
        String id = UUID.randomUUID().toString().replace("-", "").substring(0,8);
        reservation.setId(id);
        reservation.reserve();
        storage.add(reservation);
        return reservation;
    }

    @Override
    public Optional<Reservation> find(String id) {
        for (Reservation reservation : storage) {
            if (reservation.getId().equals(id)) {
                return Optional.of(reservation);
            }
        }
        return Optional.empty();
    }

    @Override
    public boolean remove(String id) {
       Optional<Reservation> reservationOptional = find(id);
       if (!reservationOptional.isPresent()) return false;
       storage.remove(reservationOptional.get());
       return true;
    }

    @Override
    public Reservation findByCustomerId(int customerId) {
        for (Reservation reservation : storage) {
            if (reservation.getCustomer().getId() == customerId) {
                return reservation;
            }
        }
        return null;
    }

    @Override
    public Reservation findByVehicleLicensePlate(String licensePlate) {
        for (Reservation reservation : storage) {
            if (reservation.getAssociatedVehicle().getLicensePlate().equals(licensePlate)) {
                return reservation;
            }
        }
        return null;
    }

    @Override
    public Reservation findByParkingSpotNumber(int spotNumber) {
        for (Reservation reservation : storage) {
            if (reservation.getParkingSpot().getSpotNumber() == spotNumber) {
                return reservation;
            }
        }
        return null;
    }
}