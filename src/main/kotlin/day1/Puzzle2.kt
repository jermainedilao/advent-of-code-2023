package day1

import openFile

fun main() {
    openFile("day1/Day1Input").readText().trim().split("\n")
        .sumOf(::findFirstAndLastDigits)
        .let(::println)
}

private fun findFirstAndLastDigits(string: String): Int {
    val first = getFirstDigit(string)
    val last = getLastDigit(string)
    return "$first$last".toInt()
}

private fun getFirstDigit(string: String): String {
    val firstWordDigit = string.findAnyOf(numbersMap.keys)
    val firstDigit = string.firstOrNull(Character::isDigit)
    return when {
        firstWordDigit?.second != null && firstDigit != null -> {
            if (firstWordDigit.first < string.indexOf(firstDigit)) {
                numbersMap[firstWordDigit.second]
            } else {
                firstDigit
            }
        }
        firstWordDigit?.second != null && firstDigit == null -> {
            numbersMap[firstWordDigit.second]
        }
        else -> {
            firstDigit
        }
    }.toString()
}

private fun getLastDigit(string: String): String {
    val lastWordDigit = string.findLastAnyOf(numbersMap.keys)
    val lastDigit = string.lastOrNull(Character::isDigit)
    return when {
        lastWordDigit?.first != null && lastDigit != null -> {
            if (lastWordDigit.first > string.lastIndexOf(lastDigit)) {
                numbersMap[lastWordDigit.second]
            } else {
                lastDigit
            }
        }
        lastWordDigit?.second != null && lastDigit == null -> {
            numbersMap[lastWordDigit.second]
        }
        else -> {
            lastDigit
        }
    }.toString()
}

private val numbersMap = mapOf(
    "zero" to 0,
    "one" to 1,
    "two" to 2,
    "three" to 3,
    "four" to 4,
    "five" to 5,
    "six" to 6,
    "seven" to 7,
    "eight" to 8,
    "nine" to 9,
)