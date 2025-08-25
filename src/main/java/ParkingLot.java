import java.util.HashMap;
import java.util.Map;

public class ParkingLot {
    private final String parkingLotName;
    private int capacity;
    private Map<String, Car> packedCars;

    public ParkingLot(String parkingLotName) {
        this(parkingLotName, 10);
    }

    public ParkingLot(String parkingLotName, int capacity) {
        this.parkingLotName = parkingLotName;
        this.capacity = capacity;
        packedCars = new HashMap<String, Car>();
    }


    public Ticket park(Car car) {
        if (capacity == 0) throw new IllegalArgumentException("Current parking lot has no capacity!");
        if (car == null) throw new IllegalArgumentException("Please park a car!");
        capacity--;
        car.updateIsParking();
        packedCars.put(car.getCarLicense(), car);
        return new Ticket(car.getCarLicense(), this.parkingLotName, false);
    }

    public void unpack(Ticket ticket) {
        if(ticket == null) throw new IllegalArgumentException("Ticket cannot be null!");
        if (!this.parkingLotName.equals(ticket.getParkingLotName())) {
            throw new IllegalArgumentException("Ticket does not match parking lot!");
        }
        Car car = packedCars.getOrDefault(ticket.getCarLicense(), null);
        if(car == null) {
            throw new IllegalArgumentException("Ticket does not match car license!");
        }
        capacity++;
        this.packedCars.remove(ticket.getCarLicense());
        car.updateIsParking();
        ticket.updateIsUsed();
        return;

    }
}
