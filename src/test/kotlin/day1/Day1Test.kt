package day1

import junit.framework.Assert.assertTrue
import org.junit.Test

class Day1Test {

    @Test
    fun part1() {
        assertTrue(fuelForMass(1969) == 654)
        assertTrue(fuelForMass(100756) == 33583)
    }

    @Test
    fun part2() {
        assertTrue(fuelForMassRecursive(1969) == 966)
        assertTrue(fuelForMassRecursive(100756) == 50346)
    }
}
