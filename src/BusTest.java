import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;

import static org.junit.jupiter.api.Assertions.assertEquals;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class BusTest {
    Bus bus = new Bus("SBSTransit");

    @Test
    @Order(1)
    void getId() {
        assertEquals("bus_1", bus.getId());
    }

    @Test
    @Order(2)
    void getLength() {
        assertEquals(3, bus.getLength());
    }

    @Test
    @Order(3)
    void testInheritance() {
        assertEquals(0, bus.getSpeed());
        assertEquals(1, bus.getPosition());

        Road road = new Road("Bugis Street", 1, 6, new int[]{0, 0});
        bus.setCurrentRoad(road);
        assertEquals(road, bus.getCurrentRoad());
        bus.move();
        bus.printStatus();
        assertEquals(2, bus.getPosition());
        assertEquals(1, bus.getSpeed());
        bus.move();
        bus.printStatus();
        assertEquals(3, bus.getPosition());
        assertEquals(1, bus.getSpeed());

        bus.setLength(5);
        assertEquals(5, bus.getLength());

        bus.setBreadth(2);
        assertEquals(2, bus.getBreadth());

        bus.setSpeed(6);
        assertEquals(6, bus.getSpeed());

        bus.setPosition(0);
        assertEquals(0, bus.getPosition());

        bus.setName("SMRT");
        assertEquals("SMRT", bus.getName());
    }
}