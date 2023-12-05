package day4

import openFile
import kotlin.math.pow

fun main() = println(solvePuzzle())

fun solvePuzzle() = openFile("day4/Day4Input").readText().trim().split("\n")
    .map { it.replace("Card \\d+:".toRegex(), "") }
    .map { it.trim().split("|") }
    .fold(0) { acc, element ->
        val points = element[0].trim().split("\\s+".toRegex())
            .intersect(element[1].trim().split("\\s+".toRegex()).toSet())
            .takeIf { it.isNotEmpty() }
            ?.let { 2.0.pow(it.size - 1) }
            ?.toInt()
        acc.plus(points ?: 0)
    }
