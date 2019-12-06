package day2

import java.io.File

fun main() {
    val lines = File("src/main/resources/day2/input.txt").readLines()

    val program: List<Int> = parseProgram(lines)
        .setInputs(12, 2)
    print(intCodeComputer(program)[0])
}

fun List<Int>.setInputs(noun: Int, verb: Int): List<Int> =
    mapIndexed { i, v ->
        when (i) {
            1 -> noun
            2 -> verb
            else -> v
        }
    }

fun parseProgram(lines: List<String>): List<Int> =
    lines
        .filter(String::isNotEmpty)
        .flatMap { it.split(",") }
        .map { it.toInt() }

fun intCodeComputer(memory: List<Int>): List<Int> {
    return try {
        (0..memory.size step 4)
            .fold(memory, ::exec)
    } catch (e: HaltingException) {
        e.codes
    }
}

private fun exec(memory: List<Int>, address: Int): List<Int> =
    when (memory[address]) {
        1 -> memory.execAt(address) { a, b -> a + b }
        2 -> memory.execAt(address) { a, b -> a * b }
        99 -> throw HaltingException(memory)
        else -> throw IllegalStateException("Unknown opcode ${memory[address]}")
    }

private fun List<Int>.execAt(i: Int, op: ((Int, Int) -> Int)) =
    replace(this[i+3], op(this[this[i+1]], this[this[i+2]]))

private fun <T> List<T>.replace(index: Int, updated: T) =
    mapIndexed { i, current -> if (i == index) updated else current }

private data class HaltingException(val codes: List<Int>) : RuntimeException()
