import java.util.*;

public class StandardParkingBoy {
    protected final HashMap<String, ParkingLot> parkingLots;

    public StandardParkingBoy(ParkingLot parkingLot) {
        this.parkingLots = new HashMap<>();
        this.parkingLots.put(parkingLot.getParkingLotName(), parkingLot);
    }

    public StandardParkingBoy(List<ParkingLot> parkingLots) {
        this.parkingLots = new HashMap<>();
        for (ParkingLot parkingLot : parkingLots) this.parkingLots.put(parkingLot.getParkingLotName(), parkingLot);
    }

    public Ticket park(Car car) {
        ParkingLot parkingLot = findParkingLot();
        if (parkingLot == null) throw new IllegalArgumentException("No available position!");
        return parkingLot.park(car);
    }

    public void fetch(Ticket ticket) {
        String parkingLotName = ticket.getParkingLotName();
        ParkingLot parkingLot = parkingLots.get(parkingLotName);
        parkingLot.fetch(ticket);
    }


    protected ParkingLot findParkingLot() {
        for (ParkingLot parkingLot : parkingLots.values()) {
            if(parkingLot.getAvailableCapacity()!=0) {
                return parkingLot;
            }
        }
        return null;
    }
}
