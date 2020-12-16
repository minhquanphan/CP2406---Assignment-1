public class Car {
    private static final int STOPPED = 0; //car speed is 0m/s
    private static final int NEXT_ROAD_INDEX = 0;
    private static final int START_POSITION = 1;
    private static final String PREFIX = "car_";
    private Road currentRoad; // current Road object
    protected static int currentCarNumber = 0;
    protected String id; // unique identifier
    protected String name;
    protected float length; // number of segments occupied, 1 for ease of prototype.
    protected float breadth;
    protected int speed; //segments moved per turn
    protected int position; // position on current road

    public Car(String name) {
        this.currentCarNumber += 1;
        // car ID has pattern car_### where ### is auto generated and incremental
        this.id = PREFIX + currentCarNumber;
        this.name = name;
        this.length = 1f; // cars made 1m long for prototype.
        this.breadth = length * 0.5f;
        this.speed = 0;
        this.position = 1;
    }

    public Car() {
        this.id = "car_000";
        this.length = 1f;
        this.breadth = this.length * 0.5f;
        this.speed = 0;
        this.position = 1;
    }

    public void move() {
        this.speed = this.currentRoad.getSpeedLimit(); //set speed limit to that of currentRoad
        if (!this.currentRoad.getLightsOnRoad().isEmpty() && this.position == this.currentRoad.getLightsOnRoad().get(0).getPosition() &&
                this.currentRoad.getLightsOnRoad().get(0).getState().equals("red")) {
            this.speed = STOPPED;
        } else {
            if (this.currentRoad.getLength() == this.getPosition() && !this.currentRoad.getConnectedRoads().isEmpty()) {
                this.currentRoad.getCarsOnRoad().remove(this);
                this.currentRoad = this.currentRoad.getConnectedRoads().get(NEXT_ROAD_INDEX);
                this.currentRoad.newCarEnteringRoad(this);
                this.position = START_POSITION;
            } else if (this.currentRoad.getLength() > this.getPosition()) {
                this.position = (this.position + this.speed);
            } else {
                this.speed = STOPPED;
            }
        }
    }

    public void printStatus() {
        System.out.printf("Vehicle %s with ID %s going with speed %dm/s on road %s (Road ID: %s, length: %s) at position:%s%n",
                this.getName(), this.getId(), this.getSpeed(), this.getCurrentRoad().getName(),
                this.getCurrentRoad().getId(), this.getCurrentRoad().getLength(), this.getPosition());
    }

    public float getLength() {
        return length;
    }

    public void setLength(float length) {
        this.length = length;
    }

    public float getBreadth() {
        return breadth;
    }

    public void setBreadth(float breadth) {
        this.breadth = breadth;
    }

    public int getSpeed() {
        return speed;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }

    public int getPosition() {
        return position;
    }

    public void setPosition(int position) {
        this.position = position;
    }

    public String getName() { return this.name; }

    public void setName(String name) {  this.name = name; }

    public Road getCurrentRoad() {
        return currentRoad;
    }

    public void setCurrentRoad(Road currentRoad) {
        currentRoad.newCarEnteringRoad(this); //add this car to the road its on.
        this.currentRoad = currentRoad;
    }

    public String getId() {
        return id;
    }
}

