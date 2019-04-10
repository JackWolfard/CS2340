package edu.gatech.cs2340.spacetrader;


import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import edu.gatech.cs2340.spacetrader.entity.ShipType;
import edu.gatech.cs2340.spacetrader.model.Ship;
import edu.gatech.cs2340.spacetrader.model.SolarSystem;
import edu.gatech.cs2340.spacetrader.model.Universe;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class aboutToTravelUnitTest {
    private static Ship testShip;
    private static Universe universe;
    private static List<SolarSystem> sysList;
    private static Map<SolarSystem, int[]> starMap;
    private Map<SolarSystem, Integer> expectedTravelMap;

    @BeforeClass
    public static void setUpBeforeClass() {
        // need a constant universe
        sysList = SolarSystem.generateSystem(10);
        List<int[]> coordsList = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            coordsList.add(new int[]{0, i * 10});
        }
        universe = new Universe(sysList, coordsList);
        starMap = universe.getStarMap();
        testShip = new Ship(ShipType.GN);
    }

    @Before
    public void setUp() {
        expectedTravelMap = new HashMap<>();
        for (int i = 1; i < sysList.size(); i++) {
            int[] coordinates = starMap.get(sysList.get(i));
            expectedTravelMap.put(sysList.get(i), coordinates[1]);
        }
    }

    @Test(expected=IllegalArgumentException.class)
    public void testForNullShip() {
        universe.aboutToTravel(null);
    }

    @Test
    public void testForCurrentPlanet() {
        assertEquals(universe.getCurrentPlanet(), sysList.get(0).getPlanet());
    }

    @Test
    public void testCanTravelAll() {
        testShip.setCurrentMileage(100);
        Map<SolarSystem, Integer> travelMap = universe.aboutToTravel(testShip);
        assertMapEquals(travelMap, expectedTravelMap);
    }

    @Test
    public void testCanTravelNone() {
        testShip.setCurrentMileage(9);
        Map<SolarSystem, Integer> travelMap = universe.aboutToTravel(testShip);
        assertMapEquals(travelMap, new HashMap<SolarSystem, Integer>());
    }

    @Test
    public void testCanTravelSome() {
        testShip.setCurrentMileage(40);
        Map<SolarSystem, Integer> travelMap = universe.aboutToTravel(testShip);
        for (int i = 5; i < sysList.size(); i++) {
            expectedTravelMap.remove(sysList.get(i));
        }
        assertMapEquals(travelMap, expectedTravelMap);
    }

    private <K, V> void assertMapEquals(Map<K, V> actual, Map<K, V> expected) {
        assertEquals(actual.size(), expected.size());
        for (K key: actual.keySet()) {
            assertTrue(expected.containsKey(key));
            assertEquals(actual.get(key), expected.get(key));
        }
    }
}