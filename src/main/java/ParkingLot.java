import java.util.HashMap;
import java.util.Map;

public class ParkingLot {
    private final String parkingLotName;
    private int capacity;
    private final Map<String, Car> packedCars;

    public ParkingLot(String parkingLotName) {
        this(parkingLotName, 10);
    }

    public ParkingLot(String parkingLotName, int capacity) {
        this.parkingLotName = parkingLotName;
        this.capacity = capacity;
        packedCars = new HashMap<String, Car>();
    }


    public Ticket park(Car car) {
        if (capacity == 0) throw new IllegalArgumentException("No available position!");
        if (car == null) throw new IllegalArgumentException("Please park a car!");
        capacity--;
        car.updateIsParking();
        packedCars.put(car.getCarLicense(), car);
        return new Ticket(car.getCarLicense(), this.parkingLotName, false);
    }

    public void fetch(Ticket ticket) {
        if(ticket == null) throw new IllegalArgumentException("Ticket cannot be null!");
        if(ticket.getIsUsed()) throw new IllegalArgumentException("Ticket is used!");
        if (!this.parkingLotName.equals(ticket.getParkingLotName())) {
            throw new IllegalArgumentException("Unrecognized parking ticket!");
        }
        Car car = packedCars.getOrDefault(ticket.getCarLicense(), null);
        if(car == null) {
            throw new IllegalArgumentException("Ticket does not match car license!");
        }
        capacity++;
        this.packedCars.remove(ticket.getCarLicense());
        car.updateIsParking();
        ticket.updateIsUsed();
    }

    public boolean isFull() {
        return capacity == 0;
    }

    public String getParkingLotName() {
        return parkingLotName;
    }
}
