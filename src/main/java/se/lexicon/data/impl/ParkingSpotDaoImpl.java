package se.lexicon.data.impl;

import se.lexicon.data.ParkingSpotDao;
import se.lexicon.model.ParkingSpot;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class ParkingSpotDaoImpl implements ParkingSpotDao {
    private List<ParkingSpot> storage = new ArrayList<>();
    @Override
    public ParkingSpot create(ParkingSpot parkingSpot) {
        if (parkingSpot == null) throw new IllegalArgumentException("Parking spot can't be null");
        if (find(parkingSpot.getSpotNumber()).isPresent()) throw new IllegalArgumentException("Parking spot already exist");
        storage.add(parkingSpot);
        return parkingSpot;
    }

    @Override
    public Optional<ParkingSpot> find(int spotNumber) {
        if (spotNumber < 1) throw new IllegalArgumentException("Spot number needs to be 1 or more.");
        for (ParkingSpot spot: storage) {
            if (spot.getSpotNumber() == spotNumber) {
                return Optional.of(spot);
            }
        }
        return Optional.empty();
    }

    @Override
    public boolean remove(int spotNumber) {
        Optional<ParkingSpot> parkingSpotOptional = find(spotNumber);
        if (!parkingSpotOptional.isPresent()) return false;
        storage.remove(parkingSpotOptional.get());
        return true;
    }

    @Override
    public List<ParkingSpot> findAll() {
        return new ArrayList<>(storage);
    }

    @Override
    public List<ParkingSpot> findByAreaCode(int areaCode) {
        if (areaCode < 1) throw new IllegalArgumentException("Area code needs to be 1 or more.");
        List<ParkingSpot> existByAreaCode = new ArrayList<>();
        for (ParkingSpot area: storage) {
            if (area.getAreaCode() == areaCode) {
                existByAreaCode.add(area);
            }
        }
        return existByAreaCode;
    }

    @Override
    public void occupyParkingSpot(int spotNumber) {
      for (ParkingSpot spot : storage) {
          if (spot.getSpotNumber() == spotNumber) {
              if (!spot.isOccupied()) {
                  spot.occupy();
              }
          }
      }
    }

    @Override
    public void vacateParkingSpot(int spotNumber) {
        for (ParkingSpot spot : storage) {
            if (spot.getSpotNumber() == spotNumber) {
                if (spot.isOccupied()) {
                    spot.vacate();
                }
            }
        }
    }
}