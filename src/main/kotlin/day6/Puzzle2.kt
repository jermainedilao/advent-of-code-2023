package day6

import openFile

fun main() {
    openFile("day6/Day6Input").readText().trim().split("\n")
        .map { it.replace("\\w+:\\s+".toRegex(), "").trim() }
        .map { it.replace("\\s+".toRegex(), "") }
        .map { it.toLong() }
        .let { list ->
            val time = list[0]
            val distanceToBeat = list[1]
            val start = time - 1
            (start downTo 1)
                .first { it * (time - it) > distanceToBeat }
                .let { it - (start - it) }
        }
        .let(::println)
}