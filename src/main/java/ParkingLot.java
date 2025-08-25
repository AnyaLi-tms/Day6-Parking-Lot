import java.util.HashMap;
import java.util.Map;

public class ParkingLot {
    private final String parkingLotName;
    private final int capacity;
    private int availableCapacity;
    private final Map<String, Car> packedCars;

    public ParkingLot(String parkingLotName) {
        this(parkingLotName, 10);
    }

    public ParkingLot(String parkingLotName, int capacity) {
        this.parkingLotName = parkingLotName;
        this.capacity = capacity;
        this.availableCapacity = capacity;
        packedCars = new HashMap<String, Car>();
    }


    public Ticket park(Car car) {
        if (availableCapacity == 0) throw new IllegalArgumentException("No available position!");
        if (car == null) throw new IllegalArgumentException("Please park a car!");
        if (car.getIsParking()) throw new IllegalArgumentException("car is parked!");
        return generateTicket(car);
    }

    private Ticket generateTicket(Car car) {
        availableCapacity--;
        car.updateIsParking();
        packedCars.put(car.getCarLicense(), car);
        return new Ticket(car.getCarLicense(), this.parkingLotName, false);
    }

    public Car fetch(Ticket ticket) {
        Car car = verify(ticket);
        availableCapacity++;
        this.packedCars.remove(ticket.getCarLicense());
        car.updateIsParking();
        ticket.updateIsUsed();
        return car;
    }

    private Car verify(Ticket ticket) {
        if (ticket == null) throw new IllegalArgumentException("Ticket cannot be null!");
        if (ticket.getIsUsed()) throw new IllegalArgumentException("Ticket is used!");
        if (!this.parkingLotName.equals(ticket.getParkingLotName())) {
            throw new IllegalArgumentException("Unrecognized parking ticket!");
        }
        Car car = packedCars.getOrDefault(ticket.getCarLicense(), null);
        if (car == null) throw new IllegalArgumentException("Ticket does not match car license!");
        return car;
    }

    public int getCapacity() {
        return capacity;
    }

    public int getAvailableCapacity() {
        return availableCapacity;
    }

    public String getParkingLotName() {
        return parkingLotName;
    }

    public void setAvailableCapacity(int availableCapacity) {
        this.availableCapacity = availableCapacity;
    }

    protected float calcAvailableRate() {
        return (float) this.availableCapacity / (float) this.capacity;
    }
}
