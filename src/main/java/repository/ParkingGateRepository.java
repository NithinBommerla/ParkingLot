package repository;

import exception.ParkingGateNotFoundException;
import model.ParkingGate;

import java.util.HashMap;

public class ParkingGateRepository {
    private HashMap<Integer, ParkingGate> parkingGateMap;
    private static int counter = 1;

    public ParkingGateRepository() { // HashMap<Integer, ParkingGate> parkingGateMap
        this.parkingGateMap = new HashMap<>();
    }

    public ParkingGate save(ParkingGate parkingGate) {
        parkingGate.setId(counter++);
        parkingGateMap.put(parkingGate.getId(), parkingGate);
        return parkingGateMap.get(parkingGate.getId());
    }

    public ParkingGate findByID(int parkingGateId) {
        if(parkingGateMap.containsKey(parkingGateId)) return parkingGateMap.get(parkingGateId);
        else throw new ParkingGateNotFoundException("Parking Gate with given id:"+parkingGateId+" doesn't exist.");
    }

    public ParkingGate update(int parkingGateId, ParkingGate newParkingGate) {
        if(parkingGateMap.containsKey(parkingGateId)) return parkingGateMap.put(parkingGateId, newParkingGate);
        else throw new ParkingGateNotFoundException("Parking Gate with given id:"+parkingGateId+" doesn't exist.");
    }

    public boolean delete(int parkingGateId) {
        if(parkingGateMap.containsKey(parkingGateId)) {
            parkingGateMap.remove(parkingGateId);
            return true;
        } else throw new ParkingGateNotFoundException("Parking Gate with given id:"+parkingGateId+" doesn't exist.");
    }
}
