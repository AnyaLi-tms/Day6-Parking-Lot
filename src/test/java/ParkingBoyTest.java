import org.junit.Test;

import static org.junit.Assert.*;

public class ParkingBoyTest {

    @Test
    public void test_boy_park(){
        // Given
        String parkingLotName = "111";
        String carId="23423";
        Car car = new Car(carId);
        ParkingLot parkingLot = new ParkingLot(parkingLotName);
        ParkingBoy parkingBoy = new ParkingBoy(parkingLot);
        // When
        Ticket ticket= parkingBoy.park(car);
        // Then
        assertTrue(car.getIsParking());
        assertEquals(carId, ticket.getCarLicense());
        assertEquals(parkingLotName, ticket.getParkingLotName());
        assertFalse(ticket.getIsUsed());
    }

    @Test
    public void test_boy_fetch(){
        // Given
        String parkingLotName = "111";
        String carId="23423";
        Car car = new Car(carId);
        ParkingLot parkingLot = new ParkingLot(parkingLotName);
        ParkingBoy parkingBoy = new ParkingBoy(parkingLot);
        Ticket ticket = parkingLot.park(car);
        // When
        parkingBoy.fetch(ticket);

        // Then
        assertTrue(ticket.getIsUsed());
        assertFalse(car.getIsParking());
    }
}
