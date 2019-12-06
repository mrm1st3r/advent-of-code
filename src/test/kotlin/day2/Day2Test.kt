package day2

import org.junit.Assert.assertEquals
import org.junit.Test

fun opcode(input: String): String {
    val ints = input.split(",").map {it.toInt()}

    return intCodeComputer(ints).joinToString(",") { it.toString() }
}

class Day2Test {

    @Test
    fun add() {
        // given:
        val input = "1,0,0,0,99"
        val expected = "2,0,0,0,99"

        // then:
        assertEquals(expected, opcode(input))
    }

    @Test
    fun multiply() {
        val input = "2,3,0,3,99"
        val expected = "2,3,0,6,99"

        assertEquals(expected, opcode(input))
    }

    @Test
    fun exitNotLastItem() {
        val input = "2,4,4,5,99,0"
        val expected = "2,4,4,5,99,9801"

        assertEquals(expected, opcode(input))
    }

    @Test
    fun earlyExit() {
        val input = "1,1,1,4,99,5,6,0,99"
        val expected = "30,1,1,4,2,5,6,0,99"

        assertEquals(expected, opcode(input))
    }
}
