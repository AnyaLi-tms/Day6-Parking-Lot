import java.util.*;

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
        ParkingLot parkingLot = findMaxParkingLotBySequence();
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

    private ParkingLot findMaxParkingLotBySequence() {
        Optional<ParkingLot> maxParkingLot = parkingLots.values().stream()
                .max(Comparator.comparingInt(ParkingLot::getCapacity));
        return maxParkingLot.orElse(null);
    }
}
