package day6

import openFile

fun main() {
    openFile("day6/Day6Input").readText().trim().split("\n")
        .map { it.replace("\\w+:\\s+".toRegex(), "").trim() }
        .map { it.split("\\s+".toRegex()).map(String::toInt) }
        .let { list ->
            val timeList = list[0]
            val distanceList = list[1]
            timeList.foldIndexed(1) { index, acc, time ->
                val distanceToBeat = distanceList[index]
                val start = time - 1
                val numWaysToWin = (start downTo 1)
                    .first { it * (time - it) > distanceToBeat }
                    .let { it - (start - it) }
                acc * numWaysToWin
            }
        }
        .let(::println)
}