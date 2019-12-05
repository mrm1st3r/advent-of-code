package day2

import java.io.File

fun main() {
    val lines = File("src/main/resources/day2/input.txt").readLines()
    val program: List<Int> = lines
        .filter(String::isNotEmpty)
        .flatMap { it.split(",") }
        .map { it.toInt() }
        .mapIndexed { i, v ->
            when (i) {
                1 -> 12
                2 -> 2
                else -> v
            }
        }
    print(intcodes(program)[0])
}

fun intcodes(input: List<Int>): List<Int> {
    return try {
        (0..input.size step 4)
            .fold(input, ::exec)
    } catch (e: HaltingException) {
        e.codes
    }
}

fun exec(codes: List<Int>, i: Int): List<Int> =
    when (codes[i]) {
        1 -> codes.execAt(i) { a, b -> a + b }
        2 -> codes.execAt(i) { a, b -> a * b }
        99 -> throw HaltingException(codes)
        else -> throw IllegalStateException("Unknown opcode ${codes[i]}")
    }

fun List<Int>.execAt(i: Int, op: ((Int, Int) -> Int)) =
    replace(this[i+3], op(this[this[i+1]], this[this[i+2]]))

fun <T> List<T>.replace(index: Int, updated: T) =
    mapIndexed { i, current -> if (i == index) updated else current }

data class HaltingException(val codes: List<Int>) : RuntimeException()
