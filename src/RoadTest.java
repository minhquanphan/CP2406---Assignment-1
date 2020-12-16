import org.junit.jupiter.api.*;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class RoadTest {
    Road road = new Road("Tanah Merah", 2, 2, new int[]{0, 0});

    @Test
    @Order(1)
    void constructor() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            new Road("Watten View", -2, 10, new int[]{0, 0});
        });
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            new Road("York Hill", 2, -10, new int[]{0, 0});
        });
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            new Road("Yishun Central", 2, 10, new int[]{0, 0, 0});
        });
    }

    @Test
    @Order(2)
    void getId() {
        assertEquals("road_5", road.getId());
    }

    @Test
    @Order(3)
    void getName() {
        assertEquals("Tanah Merah", road.getName());
    }

    @Test
    @Order(4)
    void setName() {
        road.setName("Orchard Road");
        assertEquals("Orchard Road", road.getName());
    }

    @Test
    @Order(5)
    void getSpeedLimit() {
        assertEquals(2, road.getSpeedLimit());
    }

    @Test
    @Order(6)
    void setSpeedLimit() {
        road.setSpeedLimit(50);
        assertEquals(50, road.getSpeedLimit());
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            road.setSpeedLimit(-12);
        });
    }

    @Test
    @Order(7)
    void getLength() {
        assertEquals(2, road.getLength());
    }

    @Test
    @Order(8)
    void setLength() {
        road.setLength(15);
        assertEquals(15, road.getLength());
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            road.setLength(-2);
        });
    }

    @Test
    @Order(9)
    void getStartLocation() {
        int[] expected = {0, 0};
        assertArrayEquals(expected, road.getStartLocation());
    }

    @Test
    @Order(10)
    void setStartLocation() {
        int[] previousStartLocation = {0,0};
        int[] previousEndLocation = {0 + road.getLength(),0};
        assertArrayEquals(previousStartLocation, road.getStartLocation());
        assertArrayEquals(previousEndLocation, road.getEndLocation());
        int[] updatedStartLocation = {2,5};
        int[] updatedEndLocation = {2+ road.getLength(),5};
        road.setStartLocation(updatedStartLocation);
        assertArrayEquals(updatedStartLocation, road.getStartLocation());
        assertArrayEquals(updatedEndLocation, road.getEndLocation());

        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            int[] location = {0, 0, 0};
            road.setStartLocation(location);
        });

        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            int[] location = {0, -2};
            road.setStartLocation(location);
        });

        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            int[] location = {-5, 2};
            road.setStartLocation(location);
        });
    }

    @Test
    @Order(11)
    void getEndLocation() {
        int[] expected = {2, 0};
        assertArrayEquals(expected, road.getEndLocation());
    }

    @Test
    @Order(12)
    void setEndLocation() {
        int[] previousLocation = road.getEndLocation();
        int[] updatedLocation = {previousLocation[0] + 10, previousLocation[1]};
        road.setEndLocation(updatedLocation);
        assertArrayEquals(updatedLocation, road.getEndLocation());

        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            int[] location = {0, 0, 0};
            road.setEndLocation(location);
        });

        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            int[] location = {0, -2};
            road.setEndLocation(location);
        });

        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            int[] location = {-5, 2};
            road.setEndLocation(location);
        });
    }

    @Test
    @Order(13)
    void getCars() {
        ArrayList<Car> expected = new ArrayList<>();
        assertEquals(expected, road.getCarsOnRoad());
    }

    @Test
    @Order(14)
    void setCars() {
        ArrayList<Car> cars = new ArrayList<>();
        cars.add(new Car("Honda"));
        cars.add(new Car("Kawaisaki"));
        road.setCarsOnRoad(cars);
        assertEquals(cars, road.getCarsOnRoad());
    }

    @Test
    @Order(15)
    void getLights() {
        ArrayList<TrafficLight> expected = new ArrayList<>();
        assertEquals(expected, road.getLightsOnRoad());
    }

    @Test
    @Order(16)
    void setLights() {
        ArrayList<TrafficLight> expected = new ArrayList<>();
        expected.add(new TrafficLight());
        road.setLightsOnRoad(expected);
        assertEquals(expected, road.getLightsOnRoad());
    }

    @Test
    @Order(17)
    void getConnectedRoads() {
        ArrayList<Road> expected = new ArrayList<>();
        assertEquals(expected, road.getConnectedRoads());
    }

    @Test
    @Order(18)
    void setConnectedRoads() {
        ArrayList<Road> expected = new ArrayList<>();
        expected.add(new Road("Tank Road", 1, 2, new int[]{0, 0}));
        road.setConnectedRoads(expected);
        assertEquals(expected, road.getConnectedRoads());
    }

    @Test
    @Order(19)
    void newCarEnteringRoad() {
        Bus bus1 = new Bus("Bus1");
        Bus bus2 = new Bus("Bus2");
        Car car1 = new Car("BMW");
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            road.newCarEnteringRoad(bus1);
        });
        road.printRoadInfo();
        road.setLength(6);
        road.newCarEnteringRoad(car1);
        road.newCarEnteringRoad(bus1);
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            road.newCarEnteringRoad(bus1);
        });
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            road.newCarEnteringRoad(bus2);
        });
    }

    @Test
    @Order(20)
    void createCars() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            road.createCars(3);
        });
        assertEquals(road.getCurrentVehiclesLength(), 2);
    }

    @Test
    @Order(21)
    void getCurrentVehiclesLength() {
        road.createCars(1);
        assertEquals(1, road.getCurrentVehiclesLength());
    }

    @Test
    @Order(22)
    void locationToBePrinted() {
        assertEquals("(0,0)", road.locationToBePrinted(road.getStartLocation()));
    }
}