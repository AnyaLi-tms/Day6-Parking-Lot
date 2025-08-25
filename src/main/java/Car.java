public class Car {
    private final String carLicense;
    private boolean isParking;

    public Car(String carLicense) {
        this.carLicense = carLicense;
    }

    public boolean getIsParking() {
        return isParking;
    }

    public void updateIsParking() {
        this.isParking = !this.isParking;
    }

    public String getCarLicense() {
        return carLicense;
    }
}
