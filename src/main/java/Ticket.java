public class Ticket {
    private final String carLicense;
    private final String parkingLotName;
    private boolean isUsed;

    public Ticket(String carLicense, String parkingLotName, boolean isUsed) {
        this.carLicense = carLicense;
        this.parkingLotName = parkingLotName;
        this.isUsed = isUsed;
    }

    public String getCarLicense() {
        return carLicense;
    }

    public String getParkingLotName() {
        return parkingLotName;
    }

    public boolean getIsUsed() {
        return isUsed;
    }

    public void updateIsUsed() {
        this.isUsed = !this.isUsed;
    }
}
