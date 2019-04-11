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

/**
 * JUnit for Testing aboutToTravel method in model.Universe
 *
 * Full branch coverage including thrown exceptions, for loop (all, partial, none),
 * and if statements
 *
 * @author Jack Wolfard (jwolfard3)
 */
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
        assertEquals(sysList.get(0).getPlanet(), universe.getCurrentPlanet());
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

    @Test
    public void testEmptySystem() {
        testShip.setCurrentMileage(100);
        Universe emptyUniverse = new Universe(new ArrayList<SolarSystem>(), new ArrayList<int[]>());
        Map<SolarSystem, Integer> travelMap = emptyUniverse.aboutToTravel(testShip);
        assertMapEquals(travelMap, new HashMap<SolarSystem, Integer>());
    }

    private <K, V> void assertMapEquals(Map<K, V> expected, Map<K, V> actual) {
        assertEquals(expected.size(), actual.size());
        for (K key: expected.keySet()) {
            assertTrue(actual.containsKey(key));
            assertEquals(expected.get(key), actual.get(key));
        }
    }
}