import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        //Get info needed to start sim:
        Scanner simScanner = new Scanner(System.in);

        // set values for user inputs for prototype.
        int roadSpawns = 2;
        int carSpawns = 1;
        int lightSpawns = 1;

        //Create objects:
        System.out.println("Object Creation:\n---------------------");
        System.out.println("Roads:");
        ArrayList<Road> allRoads = new ArrayList<>();
        for (int i = 0; i < roadSpawns; i++) {
            System.out.println("Please input parameters for road_" + i + "...");
            boolean invalid = true;
            int lengthInput = 0;
            while (invalid) {
                System.out.print("Length: ");
                String inputLength = simScanner.nextLine();
                try {
                    lengthInput = Integer.parseInt(inputLength);
                    if (lengthInput < 1) {
                        throw new NumberFormatException("Length must be a positive integer");
                    }
                    invalid = false;
                } catch (NumberFormatException e) {
                    System.out.println("Length must be a positive integer");
                }
            }
            int defaultSpeed = 1; // force speed limit to be 1 for prototype.
            allRoads.add(new Road(Integer.toString(i), defaultSpeed, lengthInput, new int[]{0, 0}));
        }
        System.out.println("\nRoads;");
        for (Road road : allRoads) {
            road.printRoadInfo();
        }

        System.out.println("\nCars;");
        ArrayList<Car> cars = new ArrayList<>();
        Road firstRoad = allRoads.get(0);
        for (int i = 0; i < carSpawns; i++) {
            Car car = new Car(Integer.toString(i));
            cars.add(car); // all created cars will begin on road_0.
            car.setCurrentRoad(firstRoad);
            cars.get(i).printStatus();
        }

        System.out.println("\nTraffic Lights;");
        ArrayList<TrafficLight> all_lights = new ArrayList<>();
        for (int i = 0; i < lightSpawns; i++) {
            TrafficLight light = new TrafficLight();
            light.setRoadAttachedTo(allRoads.get(0));
            all_lights.add(light); // all created lights will begin on road_0.
            all_lights.get(i).printLightStatus();
        }
        System.out.println();

        // set locations and connections:
        System.out.println("Settings:");
        allRoads.get(1).setStartLocation(new int[]{allRoads.get(0).getLength(), 0}); // place road_1 to a position at the end of road_0.
        allRoads.get(1).printRoadInfo();
        allRoads.get(0).getConnectedRoads().add(allRoads.get(1)); // connect road_0 to road_1
        System.out.println();


        //Simulation loop:
        System.out.println("Simulation:");
        Random random = new Random();
        int time = 0;
        int speedOfSim = 0;

        boolean invalid = true;
        while (invalid) {
            System.out.print("Set time scale in milliseconds: ");
            String inputSimSpeed = simScanner.nextLine();
            try {
                speedOfSim = Integer.parseInt(inputSimSpeed);
                invalid = false;
            } catch (NumberFormatException e) {
                System.out.println("Time scale must be a positive integer");
            }
        }

        int carsFinished = 0;
        while (carsFinished < cars.size()) {
            for (TrafficLight light : all_lights) {
                light.operate(random.nextInt());
                light.printLightStatus();
            }
            for (Car car : cars) {
                car.move();
                car.printStatus();
                if (car.getCurrentRoad().getConnectedRoads().isEmpty() && (car.getSpeed() == 0)) {
                    carsFinished = carsFinished + 1;
                }
            }
            time = time + 1;
            System.out.println(time + " Seconds have passed.\n");
            try {
                Thread.sleep(speedOfSim); // set speed of simulation.
            } catch (InterruptedException sim) {
                Thread.currentThread().interrupt();
            }
        }
    }
}
