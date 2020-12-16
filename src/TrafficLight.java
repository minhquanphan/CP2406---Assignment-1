import java.util.Random;

public class TrafficLight {
    private static final double CHANGE_GREEN = 0.5; // 50/50 chance of changing state.
    private static final String GREEN = "green";
    private static final String RED = "red";
    private static final String PREFIX = "light_";
    private static int currentLightNumber = 0;
    private String id;
    private String state;
    private int position;
    private Road roadAttachedTo;

    public TrafficLight() {
        this.currentLightNumber += 1;
        // TrafficLight ID has pattern light_### where ### is auto generated and incremental
        this.id = PREFIX + currentLightNumber;
        state = RED;
    }

    public void operate(int seed) {
        Random random = new Random(seed);
        double probability = random.nextDouble();
        if (probability > CHANGE_GREEN) {
            this.setState(GREEN);
        } else {
            this.setState(RED);
        }
    }

    public void printLightStatus() {
        System.out.printf("%s is %s on %s at position %s%n", this.getId(), this.getState(), this.getRoadAttachedTo().getId(), this.getPosition());
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public Road getRoadAttachedTo() {
        return roadAttachedTo;
    }

    public void setRoadAttachedTo(Road roadAttachedTo) {
        this.roadAttachedTo = roadAttachedTo;
        this.roadAttachedTo.getLightsOnRoad().add(this); // adds this light to the road it belongs to.
        this.position = this.roadAttachedTo.getLength(); // always places the traffic light at the end of the roadAttachedTo.
    }

    public int getPosition() {
        return position;
    }

    public void setPosition(int position) {
        this.position = position;
    }

    public String getId() {
        return id;
    }
}
