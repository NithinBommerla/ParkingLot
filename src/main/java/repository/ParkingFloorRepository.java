package repository;

import exception.ParkingFloorNotFoundException;
import model.ParkingFloor;

import java.util.HashMap;

public class ParkingFloorRepository {
    private HashMap<Integer, ParkingFloor> parkingFloorMap;
    private static int counter = 1;

    public ParkingFloorRepository(HashMap<Integer, ParkingFloor> parkingFloorMap) {
        this.parkingFloorMap = new HashMap<>();
    }

    public ParkingFloor save(ParkingFloor parkingFloor) {
        parkingFloor.setId(counter++);
        parkingFloorMap.put(parkingFloor.getId(), parkingFloor);
        return parkingFloorMap.get(parkingFloor.getId());
    }

    public ParkingFloor findByID(int parkingFloorId) {
        if(parkingFloorMap.containsKey(parkingFloorId)) return parkingFloorMap.get(parkingFloorId);
        else throw new ParkingFloorNotFoundException("Parking Floor with given id:"+parkingFloorId+" doesn't exist.");
    }

    public ParkingFloor update(int parkingFloorId, ParkingFloor newParkingFloor) {
        if(parkingFloorMap.containsKey(parkingFloorId)) return parkingFloorMap.put(parkingFloorId, newParkingFloor);
        else throw new ParkingFloorNotFoundException("Parking Floor with given id:"+parkingFloorId+" doesn't exist.");
    }

    public boolean delete(int parkingFloorId) {
        if(parkingFloorMap.containsKey(parkingFloorId)) {
            parkingFloorMap.remove(parkingFloorId);
            return true;
        } else throw new ParkingFloorNotFoundException("Parking Floor with given id:"+parkingFloorId+" doesn't exist.");
    }
}
