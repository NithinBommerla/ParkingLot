package repository;

import exception.ParkingLotNotFoundException;
import model.ParkingLot;

import java.util.HashMap;

public class ParkingLotRepository {
    private HashMap<Integer, ParkingLot> parkingLotMap;
    private static int counter = 1;

    public ParkingLotRepository() { // HashMap<Integer, ParkingLot> parkingLotMap
        this.parkingLotMap = new HashMap<>();
    }

    public ParkingLot save(ParkingLot parkingLot) {
        parkingLot.setId(counter++);
        parkingLotMap.put(parkingLot.getId(), parkingLot);
        return parkingLotMap.get(parkingLot.getId());
    }

    public ParkingLot findByID(int parkingLotId) {
        if(parkingLotMap.containsKey(parkingLotId)) return parkingLotMap.get(parkingLotId);
        else throw new ParkingLotNotFoundException("Parking Lot with given id:"+parkingLotId+" doesn't exist.");
    }

    public ParkingLot update(int parkingLotId, ParkingLot newParkingLot) {
        if(parkingLotMap.containsKey(parkingLotId)) return parkingLotMap.put(parkingLotId, newParkingLot);
        else throw new ParkingLotNotFoundException("Parking Lot with given id:"+parkingLotId+" doesn't exist.");
    }

    public boolean delete(int parkingLotId) {
        if(parkingLotMap.containsKey(parkingLotId)) {
            parkingLotMap.remove(parkingLotId);
            return true;
        } else throw new ParkingLotNotFoundException("Parking Lot with given id:"+parkingLotId+" doesn't exist.");
    }
}
