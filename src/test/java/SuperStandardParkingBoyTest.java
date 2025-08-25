import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class SuperStandardParkingBoyTest {
    @Test
    public void test_super_boy_park_different_capacity() {
        // Given
        String parkingLotName1 = "111";
        String parkingLotName2 = "222";
        String parkingLotName3 = "333";
        String carId = "test";
        Car car = new Car(carId);
        ParkingLot parkingLot1 = new ParkingLot(parkingLotName1,1);
        ParkingLot parkingLot2 = new ParkingLot(parkingLotName2,2);
        ParkingLot parkingLot3 = new ParkingLot(parkingLotName3, 3);
        parkingLot1.park(new Car("1"));
        parkingLot2.park(new Car("2"));
        parkingLot3.park(new Car("3"));
        List<ParkingLot> parkingLots = new ArrayList<>();
        parkingLots.add(parkingLot1);
        parkingLots.add(parkingLot2);
        parkingLots.add(parkingLot3);
        SuperStandardParkingBoy parkingBoy = new SuperStandardParkingBoy(parkingLots);
        // When
        Ticket ticket = parkingBoy.park(car);
        // Then
        assertTrue(car.getIsParking());
        assertEquals(carId, ticket.getCarLicense());
        assertEquals(parkingLotName3, ticket.getParkingLotName());
        assertFalse(ticket.getIsUsed());
    }
}
