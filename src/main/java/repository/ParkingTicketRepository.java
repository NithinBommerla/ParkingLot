package repository;

import exception.ParkingTicketNotFoundException;
import model.ParkingTicket;

import java.util.HashMap;

public class ParkingTicketRepository {
    private HashMap<Integer, ParkingTicket> parkingTicketMap;
    private static int counter = 1;

    public ParkingTicketRepository() { // HashMap<Integer, ParkingTicket> parkingTicketMap
        this.parkingTicketMap = new HashMap<>();
    }

    public ParkingTicket save(ParkingTicket parkingTicket) {
        parkingTicket.setId(counter++);
        parkingTicketMap.put(parkingTicket.getId(), parkingTicket);
        return parkingTicketMap.get(parkingTicket.getId());
    }

    public ParkingTicket findByID(int parkingTicketId) {
        if(parkingTicketMap.containsKey(parkingTicketId)) return parkingTicketMap.get(parkingTicketId);
        else throw new ParkingTicketNotFoundException("Parking Ticket with given id:"+parkingTicketId+" doesn't exist.");
    }

    public ParkingTicket update(int parkingTicketId, ParkingTicket newParkingTicket) {
        if(parkingTicketMap.containsKey(parkingTicketId)) return parkingTicketMap.put(parkingTicketId, newParkingTicket);
        else throw new ParkingTicketNotFoundException("Parking Ticket with given id:"+parkingTicketId+" doesn't exist.");
    }

    public boolean delete(int parkingTicketId) {
        if(parkingTicketMap.containsKey(parkingTicketId)) {
            parkingTicketMap.remove(parkingTicketId);
            return true;
        } else throw new ParkingTicketNotFoundException("Parking Ticket with given id:"+parkingTicketId+" doesn't exist.");
    }
}
