import java.util.Comparator;
import java.util.List;
import java.util.Optional;

public class SuperStandardParkingBoy extends SmartStandardParkingBoy {
    public SuperStandardParkingBoy(List<ParkingLot> parkingLots) {
        super(parkingLots);
    }

    protected ParkingLot findParkingLot() {
        Optional<ParkingLot> maxParkingLot = this.parkingLots.values().stream()
                .max(Comparator.comparingDouble(ParkingLot::calcAvailableRate));
        return maxParkingLot.orElse(null);
    }

}
