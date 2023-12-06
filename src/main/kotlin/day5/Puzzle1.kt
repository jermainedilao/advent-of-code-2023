@file:OptIn(ExperimentalStdlibApi::class)

package day5

import openFile

fun main() = println(solvePuzzle())

private fun solvePuzzle() = openFile("day5/Day5Input").readText().trim()
    .split("\\w+-\\w+-\\w+\\smap:".toRegex())
    .map { it.trim() }
    .let { list ->
        val seeds = list[0].replace("\\w+:".toRegex(), "")
            .trim().split("\\s".toRegex())
            .map { it.toLong() }
        val categories = list.toMutableList().apply { removeAt(0) }
        seeds.minOf { seed ->
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
            result
        }
    }

fun getDestinationFromSource(index: Int, source: Long, mapping: List<Triple<Long, Long, Long>>): Long {
    if (index >= mapping.size) return source

    val (destinationStart, sourceStart, range) = mapping[index]
    val sourceRange = sourceStart.rangeUntil(sourceStart + range)
    val result = if (source in sourceRange) {
        destinationStart + sourceRange.indexOf(source)
    } else {
        null
    }

    return result ?: getDestinationFromSource(index + 1, source, mapping)
}