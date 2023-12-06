@file:OptIn(ExperimentalStdlibApi::class, ExperimentalStdlibApi::class)

package day5

import openFile

fun main() {
    println(System.currentTimeMillis())
    println(solvePuzzle())
    println(System.currentTimeMillis())
}

private fun solvePuzzle() = openFile("day5/Day5Input").readText().trim()
    .split("\\w+-\\w+-\\w+\\smap:".toRegex())
    .map { it.trim() }
    .let { list ->
        val seeds = list[0].replace("\\w+:".toRegex(), "")
            .trim().split("\\s".toRegex())
            .map { it.toLong() }
            .chunked(2) { it[0] to it[1] }
        val categories = list.toMutableList().apply { removeAt(0) }
        seeds.minOf { (seedStart, seedRange) ->
            println("========$seedStart, $seedRange=========")
            seedStart.rangeUntil(seedStart + seedRange).minOf { seed ->
                println("Seed: $seed")
                var result = seed
                categories.forEach { category ->
                    result = getDestinationFromSource(
                        index = 0,
                        source = result,
                        mapping = category.split("\n").map { categoryItem ->
                            categoryItem.split("\\s".toRegex()).let {
                                Triple(it[0].toLong(), it[1].toLong(), it[2].toLong())
                            }
                        }
                    )
                }
                println("Result of seed $seed: $result")
                result
            }
        }
    }