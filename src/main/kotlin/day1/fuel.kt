package day1

import java.io.File

fun fuelForMass(mass: Int): Int {
    return mass / 3 - 2
}

fun fuelForMassRecursive(mass: Int): Int {
    return (mass / 3 - 2).let {
        when {
            it < 0 -> 0
            else -> it + fuelForMassRecursive(it)
        }
    }
}

fun main() {
    val lines = File("src/main/resources/day1/input.txt").readLines()
    val fuel = lines
        .filter(String::isNotEmpty)
        .map {it.toInt()}
        .map {
        fuelForMassRecursive(it)
    }.sum()
    print(fuel)
}
