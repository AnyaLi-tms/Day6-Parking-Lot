import org.junit.Test;

import static org.junit.Assert.*;

public class ParkingLotTest {
    @Test
    public void should_return_ticket_given_parking_lot_and_car_when_parking(){
        // Given
        String carId = "12345";
        String parkingLotName = "Parking Lot";
        int capacity = 20;
        ParkingLot parkingLot = new ParkingLot(parkingLotName, capacity);
        Car car = new Car(carId);

        // When
        Ticket ticket = parkingLot.park(car);

        // Then
        assertTrue(car.getIsParking());
        assertEquals(carId, ticket.getCarLicense());
        assertEquals(parkingLotName, ticket.getParkingLotName());
        assertFalse(ticket.getIsUsed());
    }
    
    @Test
    public void should_throw_exception_given_no_capacity_parking_lot_and_car_when_parking(){
        // Given
        String carId = "12345";
        String parkingLotName = "Parking Lot";
        int capacity = 0;
        ParkingLot parkingLot = new ParkingLot(parkingLotName, capacity);
        Car car = new Car(carId);

        // When
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> parkingLot.park(car));

        // Then
        assertEquals("No available position!", exception.getMessage());
    }

    @Test
    public void should_throw_exception_given_no_car_when_parking(){
        // Given
        String parkingLotName = "Parking Lot";
        ParkingLot parkingLot = new ParkingLot(parkingLotName);

        // When
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> parkingLot.park(null));

        // Then
        assertEquals("Please park a car!", exception.getMessage());
    }

    @Test
    public void should_return_null_given_ticket_when_fetching(){
        // Given
        String carId = "12345";
        String parkingLotName = "Parking Lot";
        int capacity = 20;
        ParkingLot parkingLot = new ParkingLot(parkingLotName, capacity);
        Car car = new Car(carId);
        Ticket ticket = parkingLot.park(car);

        // When
        parkingLot.fetch(ticket);

        // Then
        assertTrue(ticket.getIsUsed());
        assertFalse(car.getIsParking());
    }

    @Test
    public void should_throw_exception_given_wrong_parking_lot_ticket_when_fetching(){
        // Given
        String parkingLotName = "Parking Lot";
        int capacity = 20;
        ParkingLot parkingLot = new ParkingLot(parkingLotName, capacity);
        ParkingLot parkingLot2 = new ParkingLot("Parking Lot 2", capacity);
        Car car = new Car("1");
        Ticket ticket = parkingLot.park(car);

        // When
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> parkingLot2.fetch(ticket));
        // Then
        assertEquals("Unrecognized parking ticket!", exception.getMessage());
    }



    @Test
    public void should_throw_exception_given_wrong_car_ticket_when_fetching(){
        // Given
        String parkingLotName = "Parking Lot";
        int capacity = 20;
        ParkingLot parkingLot = new ParkingLot(parkingLotName, capacity);
        Car car = new Car("1");
        Ticket ticket = parkingLot.park(car);
        parkingLot.fetch(ticket);

        // When
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> parkingLot.fetch(ticket));
        // Then
        assertEquals("Ticket is used!", exception.getMessage());
    }


    @Test
    public void should_throw_exception_given_no_ticket_when_fetching(){
        // Given
        String parkingLotName = "Parking Lot";
        int capacity = 20;
        ParkingLot parkingLot = new ParkingLot(parkingLotName, capacity);
        Car car = new Car("1");
        Ticket ticket = parkingLot.park(car);

        // When
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> parkingLot.fetch(null));
        // Then
        assertEquals("Ticket cannot be null!", exception.getMessage());
    }
}
