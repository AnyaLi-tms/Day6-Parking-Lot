import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class ParkingBoyTest {

    @Test
    public void test_boy_park_first_not_full(){
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
    public void test_boy_park_second_not_full(){
        // Given
        String parkingLotName1 = "111";
        String parkingLotName2 = "222";
        String parkingLotName3 = "333";
        String carId="23423";
        Car car = new Car(carId);
        ParkingLot parkingLot1 = new ParkingLot(parkingLotName1,0);
        ParkingLot parkingLot2 = new ParkingLot(parkingLotName2);
        ParkingLot parkingLot3 = new ParkingLot(parkingLotName3);
        List<ParkingLot> parkingLots = new ArrayList<>();
        parkingLots.add(parkingLot1);
        parkingLots.add(parkingLot2);
        ParkingBoy parkingBoy = new ParkingBoy(parkingLots);
        // When
        Ticket ticket= parkingBoy.park(car);
        // Then
        assertTrue(car.getIsParking());
        assertEquals(carId, ticket.getCarLicense());
        assertEquals(parkingLotName2, ticket.getParkingLotName());
        assertFalse(ticket.getIsUsed());
    }

    @Test
    public void test_boy_park_all_full(){
        // Given
        String parkingLotName1 = "111";
        String parkingLotName2 = "222";
        String parkingLotName3 = "333";
        String carId="23423";
        Car car = new Car(carId);
        ParkingLot parkingLot1 = new ParkingLot(parkingLotName1,0);
        ParkingLot parkingLot2 = new ParkingLot(parkingLotName2, 0);
        ParkingLot parkingLot3 = new ParkingLot(parkingLotName3, 0);
        List<ParkingLot> parkingLots = new ArrayList<>();
        parkingLots.add(parkingLot1);
        parkingLots.add(parkingLot2);
        parkingLots.add(parkingLot3);
        ParkingBoy parkingBoy = new ParkingBoy(parkingLots);
        // When
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> parkingBoy.park(car));

        // Then
        assertEquals("No available position!", exception.getMessage());
    }

    @Test
    public void test_boy_park_different_capacity(){
        // Given
        String parkingLotName1 = "111";
        String parkingLotName2 = "222";
        String parkingLotName3 = "333";
        String parkingLotName4 = "444";
        String carId="23423";
        Car car = new Car(carId);
        ParkingLot parkingLot1 = new ParkingLot(parkingLotName1,0);
        ParkingLot parkingLot2 = new ParkingLot(parkingLotName2,10);
        ParkingLot parkingLot3 = new ParkingLot(parkingLotName3,50);
        ParkingLot parkingLot4 = new ParkingLot(parkingLotName4,7);
        List<ParkingLot> parkingLots = new ArrayList<>();
        parkingLots.add(parkingLot1);
        parkingLots.add(parkingLot2);
        parkingLots.add(parkingLot3);
        parkingLots.add(parkingLot4);
        ParkingBoy parkingBoy = new ParkingBoy(parkingLots);
        // When
        Ticket ticket= parkingBoy.park(car);
        // Then
        assertTrue(car.getIsParking());
        assertEquals(carId, ticket.getCarLicense());
        assertEquals(parkingLotName3, ticket.getParkingLotName());
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
