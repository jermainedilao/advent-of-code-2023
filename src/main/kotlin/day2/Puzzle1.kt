package day2

import openFile

fun main() = println(solvePuzzle())

private fun solvePuzzle() = openFile("day2/Day2Input").readText().trim().split("\n")
    .map { it.split(":") }
    .map { it[0].replace("Game ", "").trim().toInt() to it[1] }
    .fold(0) { prev, (gameId, games) ->
        games.split(";").map(String::trim).all {
            it.split(",").map(String::trim).all { pick ->
                when {
                    pick.contains("red") -> pick.replace(" red", "").toInt() <= 12
                    pick.contains("green") -> pick.replace(" green", "").toInt() <= 13
                    pick.contains("blue") -> pick.replace(" blue", "").toInt() <= 14
                    else -> throw IllegalStateException()
                }
            }
        }.let { isValid -> if (isValid) prev + gameId else prev }
    }
