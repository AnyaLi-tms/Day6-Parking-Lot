import java.util.Comparator;
import java.util.List;
import java.util.Optional;

public class SmartStandardParkingBoy extends StandardParkingBoy {
    public SmartStandardParkingBoy(List<ParkingLot> parkingLots) {
        super(parkingLots);
    }

    protected ParkingLot findParkingLot() {
        Optional<ParkingLot> maxParkingLot = this.parkingLots.values().stream()
                .max(Comparator.comparingInt(ParkingLot::getAvailableCapacity));
        return maxParkingLot.orElse(null);
    }

}
