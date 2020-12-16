import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;

import static org.junit.jupiter.api.Assertions.assertEquals;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class CarTest {
    Car car = new Car("Audi");

    @Test
    @Order(1)
    void getId() {
        assertEquals("car_1", car.getId());
    }

    @Test
    @Order(2)
    void testMove() {
        Road road = new Road("Bugis Street", 1, 2, new int[]{0, 0});
        car.setCurrentRoad(road);
        car.move();
        car.printStatus();
        assertEquals(2, car.getPosition());
        assertEquals(1, car.getSpeed());
        car.move();
        car.printStatus();
        assertEquals(2, car.getPosition());
        assertEquals(0, car.getSpeed());
    }

    @Test
    @Order(3)
    void getLength() {
        assertEquals(1, car.getLength());
    }

    @Test
    @Order(4)
    void setLength() {
        car.setLength(5);
        assertEquals(5, car.getLength());
    }

    @Test
    @Order(5)
    void getBreadth() {
        assertEquals(0.5, car.getBreadth());
    }

    @Test
    @Order(6)
    void setBreadth() {
        car.setBreadth(2);
        assertEquals(2, car.getBreadth());
    }

    @Test
    @Order(7)
    void getSpeed() {
        assertEquals(0, car.getSpeed());
    }

    @Test
    @Order(8)
    void setSpeed() {
        car.setSpeed(6);
        assertEquals(6, car.getSpeed());
    }

    @Test
    @Order(9)
    void getPosition() {
        assertEquals(1, car.getPosition());
    }

    @Test
    @Order(10)
    void setPosition() {
        car.setPosition(0);
        assertEquals(0, car.getPosition());
    }

    @Test
    @Order(11)
    void getName() {
        assertEquals("Audi", car.getName());
    }

    @Test
    @Order(12)
    void setName() {
        car.setName("Kia");
        assertEquals("Kia", car.getName());
    }

    @Test
    @Order(13)
    void getRoad() {
        Road road = new Road("Beach View", 1, 5, new int[]{0, 0});
        car.setCurrentRoad(road);
        assertEquals(road, car.getCurrentRoad());
    }

    @Test
    @Order(14)
    void setRoad() {
        Road road = new Road("China Street", 2, 6, new int[]{0, 0});
        car.setCurrentRoad(road);
        assertEquals(road, car.getCurrentRoad());
    }
}