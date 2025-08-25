import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class ParkingBoy {
    private final HashMap<String,ParkingLot> parkingLots;

    public ParkingBoy(ParkingLot parkingLot) {
        this.parkingLots = new HashMap<>();
        this.parkingLots.put(parkingLot.getParkingLotName(),parkingLot);
    }

    public ParkingBoy(List<ParkingLot> parkingLots) {
        this.parkingLots = new HashMap<>();
        for(ParkingLot parkingLot : parkingLots){
            this.parkingLots.put(parkingLot.getParkingLotName(),parkingLot);
        }
    }

    public Ticket park(Car car) {
        ParkingLot parkingLot = findNotFullParkingLotBySequence();
        if(parkingLot == null) {
            throw new IllegalArgumentException("No available position!");
        }
        return parkingLot.park(car);
    }

    public void fetch(Ticket ticket) {
        String parkingLotName = ticket.getParkingLotName();
        ParkingLot parkingLot = parkingLots.get(parkingLotName);
        parkingLot.fetch(ticket);
    }

    private ParkingLot findNotFullParkingLotBySequence() {
        for (ParkingLot parkingLot : parkingLots.values()) {
            if(!parkingLot.isFull()) {
                return parkingLot;
            }
        }
        return null;
    }
}
