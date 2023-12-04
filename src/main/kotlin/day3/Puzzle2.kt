package day3

import openFile

fun main() = println(solvePuzzle())

private fun solvePuzzle() = openFile("day3/Day3Input").readText().trim().split("\n")
    .let { list ->
        list.foldIndexed(0) { index, acc, element ->
            "\\*".toRegex().findAll(element).sumOf { result ->
                val rangeToCheck = (result.range.first - 1).coerceAtLeast(0)..result.range.last + 1
                val currentLine = "\\d+".toRegex().findAll(element)
                    .filter { it.range.first in rangeToCheck || it.range.last in rangeToCheck }
                val prevLine = list.getOrNull(index - 1)?.let { prev ->
                    "\\d+".toRegex().findAll(prev)
                        .filter { it.range.first in rangeToCheck || it.range.last in rangeToCheck }
                }
                val nextLine = list.getOrNull(index + 1)?.let { next ->
                    "\\d+".toRegex().findAll(next)
                        .filter { it.range.first in rangeToCheck || it.range.last in rangeToCheck }
                }
                mutableListOf<String>().apply {
                    addAll(currentLine.toList().map { it.value })
                    nextLine?.let { nextItems -> addAll(nextItems.toList().map { it.value }) }
                    prevLine?.let { prevItems -> addAll(prevItems.toList().map { it.value }) }
                }.let { gearNumbers ->
                    if (gearNumbers.size > 1) {
                        gearNumbers.fold(1) { acc, item -> acc * item.toInt() }
                    } else {
                        0
                    }
                }.toInt()
            } + acc
        }
    }
