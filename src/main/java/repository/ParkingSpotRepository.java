package repository;

import exception.ParkingSpotNotFoundException;
import model.ParkingSpot;

import java.util.HashMap;

public class ParkingSpotRepository {
    private HashMap<Integer, ParkingSpot> parkingSpotMap;
    private static int counter = 1;

    public ParkingSpotRepository(HashMap<Integer, ParkingSpot> parkingSpotMap) {
        this.parkingSpotMap = new HashMap<>();
    }

    public ParkingSpot save(ParkingSpot parkingSpot) {
        parkingSpot.setId(counter++);
        parkingSpotMap.put(parkingSpot.getId(), parkingSpot);
        return parkingSpotMap.get(parkingSpot.getId());
    }

    public ParkingSpot findByID(int parkingSpotId) {
        if(parkingSpotMap.containsKey(parkingSpotId)) return parkingSpotMap.get(parkingSpotId);
        else throw new ParkingSpotNotFoundException("Parking Spot with given id:"+parkingSpotId+" doesn't exist.");
    }

    public ParkingSpot update(int parkingSpotId, ParkingSpot newParkingSpot) {
        if(parkingSpotMap.containsKey(parkingSpotId)) return parkingSpotMap.put(parkingSpotId, newParkingSpot);
        else throw new ParkingSpotNotFoundException("Parking Spot with given id:"+parkingSpotId+" doesn't exist.");
    }

    public boolean delete(int parkingSpotId) {
        if(parkingSpotMap.containsKey(parkingSpotId)) {
            parkingSpotMap.remove(parkingSpotId);
            return true;
        } else throw new ParkingSpotNotFoundException("Parking Spot with given id:"+parkingSpotId+" doesn't exist.");
    }
}
