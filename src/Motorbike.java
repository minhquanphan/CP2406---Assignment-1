public class Motorbike extends Car {
    private static final String PREFIX = "motorbike_";
    protected static int currentMotorbikeNumber = 0;

    public Motorbike(String name) {
        super(name);
        this.currentMotorbikeNumber += 1;
        // Motorbike ID has pattern motorbike_### where ### is auto generated and incremental
        this.id = PREFIX + currentMotorbikeNumber;
        this.length = super.length * 0.5f; // Motorbike has length of 0.5 times the car's length
    }

}
