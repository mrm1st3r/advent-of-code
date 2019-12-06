package day2

import java.io.File

fun main() {
    val lines = File("src/main/resources/day2/input.txt").readLines()

    val program: List<Int> = parseProgram(lines)

    val expectedOutput = 19690720

    val possibleInputs = possibleInputs()
    println("Trying ${possibleInputs.size} input values")
    val correctInput = possibleInputs.find { input -> producesExpectedOutput(program, input, expectedOutput) }

    if (correctInput == null) {
        println("Not input found")
        return
    }
    println("found matching input: $correctInput")
    println(100 * correctInput.first + correctInput.second)
}

fun producesExpectedOutput(
    program: List<Int>,
    input: Pair<Int, Int>,
    expectedOutput: Int
): Boolean {
    return intCodeComputer(program.setInputs(input.first, input.second))[0] == expectedOutput
}

private val inputRange: IntRange = (0..99)

private fun possibleInputs(): List<Pair<Int, Int>> =
    inputRange.flatMap { a -> inputRange.map { b -> Pair(a, b) } }

