package day3

import openFile

fun main() = println(solvePuzzle())

private fun solvePuzzle() = openFile("day3/Day3Input").readText().trim().split("\n")
    .let { list ->
        list.foldIndexed(0) { index, acc, element ->
            "\\d+".toRegex().findAll(element).map { it.range to it.value }.filter { (range, _) ->
                if (element.getOrNull(range.first - 1) != null && element[range.first - 1] != '.' ||
                    element.getOrNull(range.last + 1) != null && element[range.last + 1] != '.'
                ) return@filter true
                val prev = list.getOrNull(index - 1)
                val next = list.getOrNull(index + 1)
                ((range.first - 1).coerceAtLeast(0)..range.last + 1).any {
                    prev?.getOrNull(it) != null && prev[it] != '.' ||
                            next?.getOrNull(it) != null && next[it] != '.'
                }
            }.sumOf { it.second.toInt() } + acc
        }
    }
