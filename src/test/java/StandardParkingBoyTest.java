import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class StandardParkingBoyTest {

    @Test
    public void test_boy_park_first_not_full() {
        // Given
        String parkingLotName = "111";
        String carId = "23423";
        Car car = new Car(carId);
        ParkingLot parkingLot = new ParkingLot(parkingLotName);
        StandardParkingBoy standardParkingBoy = new StandardParkingBoy(parkingLot);
        // When
        Ticket ticket = standardParkingBoy.park(car);
        // Then
        assertTrue(car.getIsParking());
        assertEquals(carId, ticket.getCarLicense());
        assertEquals(parkingLotName, ticket.getParkingLotName());
        assertFalse(ticket.getIsUsed());
    }

    @Test
    public void test_boy_park_second_not_full() {
        // Given
        String parkingLotName1 = "111";
        String parkingLotName2 = "222";
        String parkingLotName3 = "333";
        String carId = "23423";
        Car car = new Car(carId);
        ParkingLot parkingLot1 = new ParkingLot(parkingLotName1, 0);
        ParkingLot parkingLot2 = new ParkingLot(parkingLotName2);
        ParkingLot parkingLot3 = new ParkingLot(parkingLotName3);
        List<ParkingLot> parkingLots = new ArrayList<>();
        parkingLots.add(parkingLot1);
        parkingLots.add(parkingLot2);
        StandardParkingBoy standardParkingBoy = new StandardParkingBoy(parkingLots);
        // When
        Ticket ticket = standardParkingBoy.park(car);
        // Then
        assertTrue(car.getIsParking());
        assertEquals(carId, ticket.getCarLicense());
        assertEquals(parkingLotName2, ticket.getParkingLotName());
        assertFalse(ticket.getIsUsed());
    }

    @Test
    public void test_boy_park_all_full() {
        // Given
        String parkingLotName1 = "111";
        String parkingLotName2 = "222";
        String parkingLotName3 = "333";
        String carId = "23423";
        Car car = new Car(carId);
        ParkingLot parkingLot1 = new ParkingLot(parkingLotName1, 0);
        ParkingLot parkingLot2 = new ParkingLot(parkingLotName2, 0);
        ParkingLot parkingLot3 = new ParkingLot(parkingLotName3, 0);
        List<ParkingLot> parkingLots = new ArrayList<>();
        parkingLots.add(parkingLot1);
        parkingLots.add(parkingLot2);
        parkingLots.add(parkingLot3);
        StandardParkingBoy standardParkingBoy = new StandardParkingBoy(parkingLots);
        // When
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> standardParkingBoy.park(car));

        // Then
        assertEquals("No available position!", exception.getMessage());
    }

    @Test
    public void test_boy_fetch() {
        // Given
        String parkingLotName = "111";
        String carId = "23423";
        Car car = new Car(carId);
        ParkingLot parkingLot = new ParkingLot(parkingLotName);
        StandardParkingBoy standardParkingBoy = new StandardParkingBoy(parkingLot);
        Ticket ticket = parkingLot.park(car);
        // When
        standardParkingBoy.fetch(ticket);

        // Then
        assertTrue(ticket.getIsUsed());
        assertFalse(car.getIsParking());
    }
}
