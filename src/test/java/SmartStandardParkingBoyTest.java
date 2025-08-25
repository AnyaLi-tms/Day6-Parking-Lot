
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class SmartStandardParkingBoyTest {
    @Test
    public void test_smart_boy_park_different_capacity() {
        // Given
        String parkingLotName1 = "111";
        String parkingLotName2 = "222";
        String parkingLotName3 = "333";
        String parkingLotName4 = "444";
        String carId = "23423";
        Car car = new Car(carId);
        ParkingLot parkingLot1 = new ParkingLot(parkingLotName1, 0);
        ParkingLot parkingLot2 = new ParkingLot(parkingLotName2, 10);
        ParkingLot parkingLot3 = new ParkingLot(parkingLotName3, 50);
        ParkingLot parkingLot4 = new ParkingLot(parkingLotName4, 7);
        List<ParkingLot> parkingLots = new ArrayList<>();
        parkingLots.add(parkingLot1);
        parkingLots.add(parkingLot2);
        parkingLots.add(parkingLot3);
        parkingLots.add(parkingLot4);
        SmartStandardParkingBoy parkingBoy = new SmartStandardParkingBoy(parkingLots);
        // When
        Ticket ticket = parkingBoy.park(car);
        // Then
        assertTrue(car.getIsParking());
        assertEquals(carId, ticket.getCarLicense());
        assertEquals(parkingLotName3, ticket.getParkingLotName());
        assertFalse(ticket.getIsUsed());
    }
}