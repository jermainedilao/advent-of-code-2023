package day2

import openFile

fun main() = println(solvePuzzle())

private fun solvePuzzle() = openFile("day2/Day2Input").readText().trim().split("\n")
    .asSequence()
    .map { it.replace(Regex("Game\\s\\d+:\\s"), "") }
    .map { it.replace(";", ",") }
    .map { it.split(",").map(String::trim) }
    .map { game ->
        game.groupBy { it.split(" ")[1] }.mapValues { entry ->
            entry.value.map(String::trim).maxOf { value ->
                value.replace(
                    when {
                        value.contains("blue") -> " blue"
                        value.contains("red") -> " red"
                        value.contains("green") -> " green"
                        else -> throw IllegalStateException()
                    },
                    ""
                ).toInt()
            }
        }
    }
    .sumOf { it.values.fold(1) { acc, item -> acc * item }.toInt() }
